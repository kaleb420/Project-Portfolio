#include <blib.h>
#include <threads.h>
#include <queues.h>

thread_t thread_table[NUM_THREADS];  /*  Table of all active and inactive threads  */

/* ----------------------------------------------------------------------------- *
 *  'resched' is a kernel internal function that determines which thread should  *
 *  be running on the current hart.  This function searches the list of threads, *
 *  identifies a  candidate thread, then calls 'ctxsw' to  swap from the current *
 *  thread to the new thread.                                                    *
 * ----------------------------------------------------------------------------- */
void resched(void) {
	u32 current_thread=get_current_thread();
	i32 temp=list_dequeue_thread(&ready_list);
	if (temp==-1 || thread_table[temp].state!=TH_READY)
		return;
	u32 new_thread=(u32) temp;
	if (thread_table[current_thread].state==TH_RUNNING){
		thread_table[current_thread].state=TH_READY;
		list_insert_thread(&ready_list, current_thread, thread_table[current_thread].priority);
	}
	set_current_thread(new_thread);
	thread_table[new_thread].state=TH_RUNNING;
	ctxsw(&thread_table[new_thread].memorymap, &thread_table[current_thread].memorymap);
	return;
}

/*  init_threads - Initializes the thread table and sets the current  *
 *                 thread id to 0.                                    *
 *  @return - i64  Returns 0 on success or -1 on failure.             */
void init_threads(void) {
  for (u32 i=0; i<NUM_THREADS; i++)
    thread_table[i].state = TH_FREE;

  set_current_thread(0);
  thread_table[0] = (thread_t) {
      .state = TH_RUNNING,
      .parent = NUM_THREADS,
      .priority = (u32)-1,
      .memorymap = (thread_mem_t) {
	.kernel_stack = (void*)get_stack(0),
	.user_stack = (void*)(get_stack(0) - 0x1000)
      }
    };
}




#include <resched_iterative.ut>
#include <resched_queued.ut>
