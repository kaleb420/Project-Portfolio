#ifndef H_TTY
#define H_TTY

#define TTY_BUFFLEN 128
#define TTY_ECHO 0x01


/*
 *  The ring_buffer_t struct is used to store all of the information to manage a circular buffer
 *  of characters.  Each TTY contains two such buffers, an input and an output.
 */
typedef struct {              /*                                                            */
  u32 avail;                  /*  Number of resources available to this buffer's handler    */
  u32 head;                   /*  The index of the first available character in the buffer  */
  u32 count;                  /*  The number of characters in the buffer                    */
  char buffer[TTY_BUFFLEN];   /*  The buffer containing the characters                      */
} ring_buffer_t;              /*                                                            */

/* ------------------------------------------------------------------------- *
 *  NOTE - Avail and Count                                                   *
 *    Avail and count serve very similar purposes in the buffer.  Count is   *
 *    meant to represent the raw number of characters in the buffer, while   *
 *    avail is the semaphore-like resource counter.  For input buffers,      *
 *    these are the same value - number of available characters - but for    *
 *    output buffers, the number of "available" slots is the inverse of the  *
 *    count, as we add more characters, we have less available space.        *
 *                                                                           *
 *    In short, avail is for waiting, when avail is 0 in either direction    *
 *    the driver should wait until more space/characters are available.      *
 * ------------------------------------------------------------------------- */



/*
 *  The tty_t struct represents all of the metadata used to manage a TeleTYpe device.
 *  This struct is allocated each time a new TTY device is initialized and attached
 *  to an underlying hardware device like a UART.
 */
typedef struct {       /*                                                 */
  ring_buffer_t in;    /*  The ingest buffer for received characters      */
  ring_buffer_t out;   /*  The egress buffer for sent characters          */
  u32 lock;            /*  A mutex lock for preventing race conditions    */
  u8  flags;           /*  Field describing behavior options for the TTY  */
} tty_t;               /*                                                 */

i64 init_tty(const char*, u64, u8);

#endif
