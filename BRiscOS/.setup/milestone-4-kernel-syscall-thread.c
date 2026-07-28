#include <blib.h>
#include <syscall.h>
#include <interrupts.h>
#include <threads.h>

/* ------------------------------------------------------------------ *
 * NOTE - syscalls                                                    *
 *                                                                    *
 * functions that return the 'syscall_t' type will throw a compiler   *
 * error if called directly.  Instead, these functions should only be *
 * referenced through the syscall function.                           *
 * ------------------------------------------------------------------ */



/* join - This function blocks the current thread until the  *
 *        requested thread enters the TH_DEFUNCT state.      *
 * @threadid - The thread id to await.                       *
 * @return   - The thread id that was awaited or -1 on error */
syscall_t join(u32 threadid) {



  return 0;
}


/* resume - This function  sets  the requested  thread to  the *
 *          TH_READY state, making it available for scheduling *
 * @threadid - The thread id to resume.                        *
 * @return   - The thread id that was resumed or -1 on error   */
syscall_t resume(u32 threadid) {



  return 0;
}


/* suspend - This function sets the requested thread to the    *
 *          TH_SUSPEND state, removing it from scheduling.     *
 * @threadid - The thread id to suspend.                       *
 * @return   - The thread id that was suspended or -1 on error */
syscall_t suspend(u32 threadid) {



  return 0;
}












/*  The following syscalls and  associated helper  functions  *
 *  are already implemented due to their relative complexity. *
 *                                                            *
 *  If you are feeling ambitious scan through the code below  *
 *  for  more  insight  into thread  creation  and  deletion. */
extern u32 context_stash_size;
i64 syscall(syscall_e, ...);
void fresh_thread_entry(void);

syscall_t kill(u32 threadid) {
  if (threadid >= NUM_THREADS || thread_table[threadid].state == TH_FREE) return -1;

  for (int i=0; i<NUM_THREADS; i++)             /*                            */
    if (thread_table[i].parent == threadid)     /* Free all running children  */
      thread_table[i].state = TH_FREE;          /* and  set  this thread  to  */
  thread_table[threadid].state = TH_DEFUNCT;    /* TH_DEFUNCT.                */

  resched();
  return threadid;
}


/* 
 * This function acts as a trampoline into the created process.  First it calls
 * the thread's function.  Then - on return - stores the result and invokes kill
 * on itself.
 */
static void _wrapper([[gnu::unused]] u64 _, u8 (*proc)(char*)) {
  u32 threadid = get_current_thread();
  u8* stack = (u8*)thread_table[threadid].memorymap.user_stack;   /*  Find the user stack associated with this thread  */
  u8* arg = stack + (sizeof(u64) * context_stash_size);           /*  Find the arguments relative to the user stack    */
  thread_table[threadid].retval = proc((char*)arg);
  syscall(KILL, threadid);
}

/* create - Create a new thread by finding space on the thread table and *
 *          building the initial stack for the new thread.               */
syscall_t create(void* proc, u16 priority, char* arg, u32 arglen) {
  u32 i, j, padded_arglen = (arglen + 8) & ~0x3U;                       /*  Calculate the space  needed for arguments  */
  for (i=0; i<NUM_THREADS && thread_table[i].state != TH_FREE; i++);    /*  and find an open slot in the thread table  */

  if (i == NUM_THREADS) return -1;
  if (arglen > THREAD_STACK_SZ / 2) return -2;

  u64* kernel_stack = (u64*)get_stack(i) - 1;                                   /*  Calculate the top of the kernel stack  */
  u64* args_addr    = kernel_stack - ((0x1000 + padded_arglen) / sizeof(u64));  /*  Calculate the bottom of the arg list   */
  u64* user_stack   = args_addr - context_stash_size;                           /*  Calculate the top of the user stack    */

  for (j=0; j<padded_arglen; j++)
    ((u8*)args_addr)[j] = (j < arglen ? arg[j] : '\0');                 /*  Copy the args onto the user stack  */
  
  kernel_stack[0] = (u64)fresh_thread_entry;                            /*  Setup the return address when switched to in  */
  user_stack[0]   = (u64)_wrapper;                                      /*  kernel space.  Then set up the user space     */
  user_stack[2]   = (u64)proc;                                          /*  stack so you land in a working thread.        */
  
  thread_table[i] = (thread_t) {                                        /*                                           */
    .state = TH_SUSPEND,                                                /*                                           */
    .parent = get_current_thread(),                                     /*  Set up the thread's table entry to it's  */
    .priority = priority,                                               /*  initial state.                           */
    .memorymap = (thread_mem_t) {                                       /*                                           */
      .kernel_stack = kernel_stack,                                     /*                                           */
      .user_stack   = user_stack,                                       /*                                           */
    }                                                                   /*                                           */
  };
  return i;
}



#include <threads.ut>
#include <threads_queued.ut>
