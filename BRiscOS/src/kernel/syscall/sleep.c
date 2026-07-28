#include <blib.h>
#include <threads.h>
#include <interrupts.h>
#include <queues.h>
#include <syscall.h>

queue_head_t sleep_list = {0};      /*  Head of the list of sleeping threads  */


/*  sleep - This function causes the requested thread to be placed onto the sleep list  *
 *          and switch to the TH_SLEEP state.                                           *
 *  @threadid - u32   The thread to be slept                                            *
 *  @delay    - u32   The number of milliseconds to sleep the thread                    *
 *  @return   - i32   The thread id slept or -1 on error                                */
syscall_t sleep(u32 threadid, u64 delay) {
	if (thread_table[threadid].state!=TH_READY)
		return -1;
	list_dequeue_thread(&sleep_list);
	list_insert_thread(&sleep_list, threadid, (i64) delay);
	thread_table[threadid].state=TH_SLEEP;
	resched();
	return threadid;
}


/*  unsleep - This function causes the requested thread to be removed from the sleep  *
 *            list and switch to the TH_READY state.                                  *
 *  @threadid - u32   The thread to be slept                                          *
 *  @return   - i32   The thread id awoken or -1 on error                             */
syscall_t unsleep(u32 threadid) {
	if (thread_table[threadid].state!=TH_SLEEP)
		return -1;
	list_remove_thread(&sleep_list, threadid);
	list_insert_thread(&ready_list, threadid, 0);
	thread_table[threadid].state=TH_READY;
	resched();
	return threadid;
}



#include <sleep.ut>
