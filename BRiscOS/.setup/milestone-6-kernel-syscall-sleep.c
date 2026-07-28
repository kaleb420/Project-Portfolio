#include <blib.h>
#include <threads.h>
#include <interrupts.h>
#include <queues.h>

queue_head_t sleep_list = {0};      /*  Head of the list of sleeping threads  */


/*  sleep - This function causes the requested thread to be placed onto the sleep list  *
 *          and switch to the TH_SLEEP state.                                           *
 *  @threadid - u32   The thread to be slept                                            *
 *  @delay    - u32   The number of milliseconds to sleep the thread                    *
 *  @return   - i32   The thread id slept or -1 on error                                */
syscall_t sleep(u32 threadid, u64 delay) {



  return 0;
}


/*  unsleep - This function causes the requested thread to be removed from the sleep  *
 *            list and switch to the TH_READY state.                                  *
 *  @threadid - u32   The thread to be slept                                          *
 *  @return   - i32   The thread id awoken or -1 on error                             */
syscall_t unsleep(u32 threadid) {



  return 0;
}



#include <sleep.ut>
