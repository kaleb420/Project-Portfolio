#include <devices.h>
#include <blib.h>

#define status(x) (x[2] & 0xE)
#define is_idle(x) (x[5] & 0x20)

/*  uart_puts - This function is invoked automatically by the kernel to handle *
 *      user requests to write characters to a uart device.                    *
 *          (see `kernel/device/external.c`)                                   *
 *  @dev    - device_t*  Pointer to the device to write to.                    *
 *  @handle - u64        Unique handle for the request (unused by uart)        *
 *  @buffer   - char* Array of characters, pased in by the user, to be printed *
 *                    to the device.                                           *
 *  @len      - u64   Number of characters to write to the device.             *
 *  @return   - i64   Number of characters successfully written to the device  */
static i64 uart_puts(device_t* dev, [[gnu::unused]] u64 handle, const char* buffer, u64 len) {
  if (dev == NULL)
    return -1;
  
  u8* uart = dev->addr;                            /*  Get the uart device from the table  */
  for (u64 i=0; i<len; i++) {                      /*  For each character in the buffer    */
    while (!is_idle(uart));                        /*  wait until the uart output is free  */
    uart[0] = (buffer[i] & 0xff);                  /*  write a character to the uart       */
  }
  return (i64)len;
}



/*  uart_gets - This function is invoked automatically by the kernel to handle *
 *      user requests to read characters from a uart device.                   *
 *          (see `kernel/device/external.c`)                                   *
 *  @dev    - device_t*  Pointer to the device to write to.                    *
 *  @handle - u64        Unique handle for the request (unused by uart)        *
 *  @buffer   - char* Array of characters, pased in by the user, into which    *
 *                    the device should write characters.                      *
 *  @len      - u64   Number of characters to read from the device.            *
 *  @return   - i64   Number of characters successfully read from the device   */
static i64 uart_gets(device_t* dev, [[gnu::unused]] u64 handle, char* buffer, u64 len) {
  if (dev == NULL)
    return -1;
  
  u64 i=0, ch;
  do {                                             /*                                        */
    if ((ch = (u64)dev->context & 0xff) == NULL)   /*  While there is space in the buffer    */
      return -1;                                   /*  Try to read a character from the      */
    dev->context = NULL;                           /*  device, fail with -1 if no character  */
    buffer[i] = (char)(ch == '\r' ? '\n' : ch);    /*  exists.                               */
    i += 1;                                        /*  Write the character into the buffer   */
  } while (i < len && buffer[i-1] != '\n');        /*                                        */
  return (i64)i;
}



/*  handle_uart - This function is invoked automatically by the kernel when    *
 *      an interrupt is detected on the associated device.                     *
 *          (see `kernel/device/external.c`)                                   *
 *  @devid    - u64   Unique Identifier used to determine which device         *
 *                    triggered the interrupt.                                 *
 *  @return   - void                                                           */
static void handle_uart(device_t* dev) {
  if (dev == NULL)
    return;
  
  #define RX_INTR 0x4

  u8* uart = dev->addr;                            /*  Get the uart device from the table                */
  if (status(uart) == RX_INTR)                     /*  If the interrupt was a characture receive event,  */
    dev->context = (void*)((u64)uart[0] & 0xff);   /*  write the char into the device context            */
}


/*  init_uart - Configure the Universal Asynchronous Receiver/Transmiter. This  *
 *              function sets  all of the  UART registers  to baseline values,  *
 *              then registers it as a system device.                           *
 *  @namespace - char*  The unique name by which this device can be referenced  *
 *                      when using the system call IO functions.                *
 *  @return    - i64    Returns 0 on success or -1 on failure.                  */
i64 init_uart(const char* namespace) {
  #define UART_ADDR ((u8*)0x10000000)
  #define UART_INTR 0xa
  #define UART_PRIO    *(u32*)0xc000028
  #define UART_ENABLED *(u32*)0xc002080

  #define UART_FREQ 1843200
  #define UART_BAUD 115200

  #define UART_CFG_ON  0x80
  #define UART_PARITY  0x08
  #define UART_8BIT    0x03
  #define UART_TRIGGER 0x08

  #define UART_RX_ON 0x01
  #define UART_TX_ON 0x02

  u8* uart = UART_ADDR;
  u32 divisor = UART_FREQ / (16 * UART_BAUD);  /*                             */
  UART_PRIO = UART_PRIO | 0x7;                 /*  Turn on the UART hardware  */
  UART_ENABLED = UART_ENABLED | 0x400;         /*                             */

  uart[3] = UART_CFG_ON;              /*  - Set the UART to configuration  mode  */
  uart[2] = 0;                        /*  - Disable  all  extra  UART  features  */
  uart[0] = divisor & 0xff;           /*  - Set the UART speed based on divisor  */
  uart[1] = (divisor >> 8) & 0xff;    /*                 (set most second byte)  */
  uart[3] = UART_PARITY | UART_8BIT;  /*  - Enable  parity bit  and 8-bit  mode  */
  uart[1] = UART_RX_ON;               /*  - Begin listening for keyboard events  */
  uart[4] |= UART_TRIGGER;            /*  - Enable device's interrupts           */

  return register_device(namespace, &(device_t) {   /*                                     */
      .addr      = UART_ADDR,                       /*  Register the device with the       */
      .interrupt = UART_INTR,                       /*  kernel's device management system. */
      .handler = handle_uart,                       /*                                     */
      .read    = uart_gets,                         /*   (see `kernel/device/external.c`)  */
      .write   = uart_puts,                         /*                                     */
    });
}
