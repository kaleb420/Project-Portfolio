#include <interrupts.h>
#include <devices.h>
#include <blib.h>



/*  handle_timer - This function is called by the kernel  each time the timer is triggered  *
 *                 It checks to see if any sleeping threads need to be awoken and rescheds  */
void handle_timer(void) {



}


/*  init_timer - This function enables the supervisor timer so that the kernel will  *
 *               trigger an interrupt every 20ms.                                    */
void init_timer(void) {
  set_interrupt(INTR_S_TIMER);
}



#include <timer.ut>
