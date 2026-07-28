#include <blib.h>
#include <devices.h>
#include <heap.h>
#include <tty.h>
#include <futex.h>
#include <mutex.h>
#include <threads.h> 

static void set_uart_transmit(device_t*, bool);

/*  tty_gets - The  `tty`  read driver function.   `tty_gets` attempts to  get characters  from the  *
 *             associated device.   If no character is available in the tty input queue,  it places  *
 *             the thread onto a futex queue based on the tty input lock and reschedules the thread  *
 *             Returns  only when  the tty device  receives `len` characters  or the `\n` delimiter  *
 *             character.                                                                            *
 *  @dev    - device_t*  Pointer to the device the user would like to read from.                     *
 *  @handle - u64        Id of the unique handle for the request (unused by tty)                     *
 *  @buffer - char*      The buffer into which characters received from the device are written.      *
 *  @len    - u64        The maximum number of characters receivable before forcing a return.        *
 *  @return - i64        The number of characters read from the device or -1 on failure.             */
static i64 tty_gets(device_t* dev, [[gnu::unused]] u64 handle, char* buffer, u64 len) {
  tty_t* tty = (tty_t*)dev->context;




  return 0;
}


/*  tty_puts - The  `tty`  write driver  function.   `tty_puts` attempts to place a  character into  *
 *             the associated  device through the `tty` output queue.   If the output queue is full  *
 *             the thread is placed ont oa futex queue based on the tty output lock and reschedules  *
 *             the thread.  Returns once `len` characters have been written to the output queue.     *
 *  @dev    - device_t*  Pointer to the device the user would like to write to.                      *
 *  @handle - u64        Id of the unique handle for the request (unused by tty)                     *
 *  @buffer - char*      The buffer from which characters should be read into the device from.       *
 *  @len    - u64        The maximum number of characters writable before returning.                 *
 *  @return - i64        The number of characters written to the device or -1 on failure.            */
static i64 tty_puts(device_t* dev, [[gnu::unused]] u64 handle, const char* buffer, u64 len) {
  tty_t* tty = (tty_t*)dev->context;
  device_t* uart = dev->origin;




  return 0;
}


/*  handle_tty - The  `tty`  interrupt handler function.  This function is invoked automatically by  *
 *               the kernel whenever an external interrupt occurs and the  Platform Level Interrupt  *
 *               Controller (PLIC) indicates that the source matches the uart device.                *
 *               Two types of interrupts may be intercepted: UART_RX_INTR indicates that the device  *
 *               has received a character, while UART_TX_INTR indicates that the device is availble  *
 *               for transmission.  On RX, the function places the new character into the `tty` out  *
 *               buffer, while on TX, we take a charcter (if any) off of the `tty` in buffer.        *
 *  @dev    - device_t*  A pointer to the tty device that was delegated to handle the interrupt.     *
 *  @return - void                                                                                   */
static void handle_tty(device_t* dev) {
  #define UART_STATUS  0x2
  #define UART_RX_INTR 0x4
  #define UART_TX_INTR 0x2
  #define UART_RW 0x0

  tty_t* tty = (tty_t*)dev->context;
  device_t* uart = dev->origin;
  u8 status = uart->addr[UART_STATUS];

  /*  Interrupt indicates there was a keypress                    */
  if (status == UART_RX_INTR) {
    uart->context = (void*)((u64)uart->addr[UART_RW] & 0xff);   /* Patch the UART context so uart_gets still works */
    char ch = (u64)uart->context & 0xff;                        /* Retreive the new character for local tty use    */





  }
  /*  Interrupt indicates the UART is ready to output a character */
  else if (status == UART_TX_INTR) {





  }
}

/*  init_tty - Initialize the `tty_in_sem` and  `tty_out_sem` semaphores for  *
 *             later TTY calls.                                               *
 *  @flags  - u8   An 8-bit length bit vector indicating which features this  *
 *             tty should enable, such as  TTY_ECHO  to also output any char  *
 *             that it ingests.                                               *
 *  @return - i32  Returns the uart device id on success, -1 on failure.      */
i64 init_tty(const char* namespace, u64 uarthandle, u8 flags) {
  tty_t* tty;
  if ((tty = kmalloc(sizeof(tty_t))) == NULL)
    return -1;

  tty->flags = flags;
  create_mutex(&tty->lock);
  tty->in  = (ring_buffer_t){ .head=0, .count=0, .avail=0 };
  tty->out = (ring_buffer_t){ .head=0, .count=0, .avail=TTY_BUFFLEN };

  device_t* uart = get_device_by_handle(uarthandle);
  i64 ttyid = register_device(namespace, &(device_t) {
      .addr    = (u8*)(u64)uart,
      .handler = handle_tty,
      .read    = tty_gets,
      .write   = tty_puts,
      .context = (void*)tty,
    });
  delegate_to_device(uarthandle, (u64)ttyid);
  return ttyid;
}


/*  set_uart_transmit - Sets the uart  device's interrupt  mechanism to either allow  *
 *                      or ignore interrupts indicating space is available.  If this  *
 *                      interrupt  is left  on when no  writes are pending,  it will  *
 *                      trigger infinitely and lock the hart.                         *
 *  @uart   - device_t*  UART device descriptor to be modified.                       *
 *  @enable - bool       Enable/disable the interrupt.                                *
 *  @return - void                                                                    */
static void set_uart_transmit(device_t* uart, bool enabled) {
  #define UART_INTR_REG 0x1
  #define UART_TX_ON    0x2
  u8 state = uart->addr[UART_INTR_REG];
  uart->addr[UART_INTR_REG] = (u8)(enabled ? (state | UART_TX_ON) : (state & ~UART_TX_ON));
}



#include <tty.ut>
