#include <blib.h>
#include <devices.h>

/*
 *  The code in this file is all run in Machine Privilege Mode and should be treated with 
 *  extreme care.
 */
static const u32 timer_interval = 100000;
static volatile u64* clint_timer_addr = ((u64*)0x2004000);

[[gnu::interrupt("machine")]]
void delegate_clk(void) {
  *clint_timer_addr += timer_interval;
}

i32 reset_timer(void);

/*  handle_exception - This function is used to  request hardware features.   If the  *
 *                     cause is not ecall, this is considered an unrecoverable error  *
 *                     in the kernel code                                             */
[[gnu::interrupt("machine")]]
void handle_exception(void) {
  if (reset_timer() == 0) {
	  kprintf("\nKERNEL PANIC - The kernel encountered an unrecoverable error\n\n");
    /*  Milestone 1 - Add Kernel Panic message  */



    while (1);
  }
}
