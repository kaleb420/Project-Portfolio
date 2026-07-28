#include <interrupts.h>
#include <devices.h>
#include <blib.h>
#include <sleep.h>
#include <queues.h>
#include <threads.h>


/*  handle_timer - This function is called by the kernel  each time the timer is triggered  *
 *                 It checks to see if any sleeping threads need to be awoken and rescheds  */
void handle_timer(void) {
	while (1){
		i32 temp=list_dequeue_thread(&sleep_list);
		if (temp==-1)
                        break;
		thread_table[temp].state=TH_READY;
		list_insert_thread(&ready_list, (u32) temp, thread_table[temp].priority);
	}
	resched();
}


/*  init_timer - This function enables the supervisor timer so that the kernel will  *
 *               trigger an interrupt every 20ms.                                    */
void init_timer(void) {
  set_interrupt(INTR_S_TIMER);
}



#include <timer.ut>
