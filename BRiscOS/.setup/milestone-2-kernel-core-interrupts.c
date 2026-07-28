#include <interrupts.h>
#include <syscall.h>
#include <devices.h>

#define S_TIMER  0x8000000000000005
#define S_EXTERN 0x8000000000000009
                                                         /* Generate function prototypes   */
#define PROTOTYPE(name, fn) i64 fn(i64, i64, i64, i64);  /* from the SYSCALL_LIST          */
SYSCALL_LIST(PROTOTYPE)                                  /* (see shared/include/syscall.h) */

#define ENUMERATE(name, fn) fn,                          /* Generate array of system calls */
  static i64 (*syscall_table[])(i64, i64, i64, i64) = {  /* from the SYSCALL_LIST          */
  SYSCALL_LIST(ENUMERATE)                                /* (see shared/include/syscall.h) */
};


/*  handle_interrupt - This function is invoked automatically by the kernel   *
 *      when an external device triggers and interrupt.                       *
 *         (see `kernel/device/external.c`)                                   *
 *  @result - i64   This argument acts as a passthrough to simplify interrupt *
 *                  handling.  It should be ignore and not used (see below).  *
 *  @cause  - u64   The integer cause of the interrupt.  This will be one of: *
 *                  [S_TIMER, S_EXTERN, S_SOFTWARE], though our kernel does   *
 *                  not make use of S_SOFTWARE.                               *
 *  @return - i64   The passthrough of the `result` variable. Always return   *
 *                  the passed in value of @result.                           */
void handle_external(void);
trap_t handle_interrupt(i64 result, u64 cause) {
  switch (cause) {
  case S_TIMER: 
    acknowledge_interrupt(INTR_S_TIMER);
    /*  [Milestone 6] - Add a handler for timer events  */

    break;
  case S_EXTERN:
    handle_external(); break;
  }
  return result;
}

/*  handle_syscall - This function  is invoked by the kernel  whenever a user thread  *
 *                   calls the `syscall` function.   It's indirectly invoked through  *
 *                   an exception which allows the kernel to be  completely isolated  *
 *                   from the user code.                                              *
 *  @idx     - i64  The systemcall number requested by the user.                      *
 *  @a1-a4   - i64  These  arguments are anonymous  aliases for whatever one to four  *
 *                  arguments were passed into the system call by the user.           *
 *  @return  - i64  Return value of the requested system call or -1 if the requested  *
 *                  call doesn't exist.                                               */
trap_t handle_syscall(i64 idx, i64 a1, i64 a2, i64 a3, i64 a4) {
  if (idx >= 0 && idx < NUM_SYSCALLS)
    return syscall_table[idx](a1, a2, a3, a4);
  return -1;
}

/*  init_interrupts - This function sets up the supervisor interrupt system.  */
void supervisor_traps(void);
void init_interrupts(void) {
  set_interrupt_handler(supervisor_traps);
  set_interrupt(INTR_S_EXTERN);
  enable_interrupts();
}
