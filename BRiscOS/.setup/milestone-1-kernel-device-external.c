#include <devices.h>
#include <config.h>
#include <blib.h>

/*
 *  This file manages the hardware (external) devices that our kernel can talk to.
 *  Each device will have it's own driver - a set of handler functions - that can be
 *  written seperately, then attached to/associated with the device here.
 */

static device_t device_table[NUM_DEVS] = {0};   /*  A list of active and initialized devices     */
static const char* name_table[NUM_DEVS] = {0};  /*  A list of names associated with each device  */
static u32 devcount = 0;


/*  get_device_by_name - Query the device table for a device by it's namespace.  *
 *                       This  is the name  associated with  the device  during  *
 *                       registration.                                           *
 *  @namespace -  char*      Name of the device to query.                        *
 *  @return    -  device_t*  Matching device or NULL if no such device exists.   */
device_t* get_device_by_name(const char* namespace) {
  u32 i, j;
  for (i=0; i<devcount; i++) {
    for (j=0; name_table[i][j] && namespace[j] && name_table[i][j] == namespace[j]; j++);
    if ((namespace[j] == ':' || namespace[j] == '\0') && name_table[i][j] == '\0')
      return &device_table[i];
  }
  return NULL;
}

/*  get_device_by_handle - Query the device table  for a device by it's unique id.  *
 *                       This is the table index associated with the device during  *
 *                       registration.                                              *
 *  @handle -  u64        Index of the device to query.                             *
 *  @return -  device_t*  Matching device or NULL if no such device exists.         */
device_t* get_device_by_handle(u64 handle) {
  return (handle >= NUM_DEVS ? NULL : &device_table[handle]);
}

/*  register_device - This function allows a driver to associate with a device  *
 *                    by adding  itself to the  list of active  devcies.  Each  *
 *                    device has a number of handles to manage different types  *
 *                    of input/output on the device.                            *
 *  @namespace - char*      A unique name to associate  with this device which  *
 *                          can be  queried  through the  `get_device_by_name`  *
 *                          function.                                           *
 *  @dev       - device_t*  The  device description  that includes  all of the  *
 *                          handler functions used by this driver.              *
 *  @return    - i64        Unique identifier for the device.                   */
i64 register_device(const char* namespace, device_t* dev) {
  if (devcount >= NUM_DEVS) return -1;
  memcpy(&device_table[devcount], dev, sizeof(device_t));
  name_table[devcount] = namespace;
  return (i32)devcount++;
}

/*  handle_external - This  function is invoked automatically  by the kernel  when  *
 *                    an interrupt occurs that was triggered by an external device. *
 *                    It  identifies which  device  triggered the  interrupt, then  *
 *                    delegates the request to that device's handler.               */
void handle_external(void) {
  #define PLIC_PENDING_DEVICE *(u32*)0xc001000             /*                      */
  u32 handled = 0, interrupts = PLIC_PENDING_DEVICE;       /* Get the interrupt ID */
  for (int i=0; i<NUM_DEVS; i++) {                         /*                      */
    if (interrupts & (0x1 << device_table[i].interrupt))   /* If the devtable has  */
      device_table[i].handler(&device_table[i]);           /* a device listening   */
    handled |= 0x1U << device_table[i].interrupt;          /* for that interrupt,  */
  }                                                        /* call that handler.   */
  PLIC_PENDING_DEVICE &= ~handled;                         /*                      */
}


/*  This function is used by the following function to allow drivers to call other  *
 *  drivers automatically.  It does not need to be used directly.                   */
static void delegate_handler(device_t* dev) {
  device_table[dev->delegate].handler(&device_table[dev->delegate]);
}

/*  delegate_to_device - This function asks for interrupts triggered by an `origin` device  *
 *                       to instead be forewarded to a `delegate` device.                   *
 *  @origin   - u64  The handle id for the device that should no longer handle interrupts.  *
 *  @delegate - u64  The handle id for the device that should now handle these interrupts.  * 
 *  @return   - i64  The handle of the `delegate` on success or -1 on failure.              *
 */
i64 delegate_to_device(u64 origin, u64 delegate) {
  if (origin >= NUM_DEVS || delegate >= NUM_DEVS || device_table[delegate].interrupt != 0)
    return -1;

  device_table[origin].delegate = delegate;
  device_table[origin].handler = delegate_handler;
  device_table[delegate].origin = &device_table[origin];
  return (i32)delegate;
}


/*  init_external - This function enables interrupts in the  Platform Level     *
 *                  Interrupt Controller (PLIC) so that the kernel hears them.  *
 *  @return - i64  Returns 0 on success or -1 on failure.                       */
i64 init_external(void) {
  #define PLIC_THRESHOLD    *(u32*)0xc201000

  PLIC_THRESHOLD = 0x0;
  return 0;
}
