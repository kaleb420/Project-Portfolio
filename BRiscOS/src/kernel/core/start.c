#include <blib.h>
#include <devices.h>
#include <interrupts.h>
#include <threads.h>
#include <queues.h>
#include <sleep.h>
#include <futex.h>
#include <tty.h>
#include <fs.h>

/*  This function contains any necessary initialization for devices  *
 *  or subsystems before the kernel passes control back to the user  *
 *  We will expand on this function regularly over the course as we  *
 *  add new functionality to the kernel.                             */
static void initialize(void) {
	init_external();
	i64 uart=init_uart(UART);
	init_tty("TTY", (u64) uart, TTY_ECHO);
	i64 handle=init_ramdisk("ramdisk", 0);
	init_fs("fs", (u64) handle);
	init_interrupts();
	init_threads();
	init_queues();
	init_timer();
	init_futex();
}



/* This function reads a  file from a mounted disk  into memory and returns *
 * the address of the starting address of the executable binary. (See NOTE) */
static void* load_executable([[gnu::unused]] const char* path) {
  extern u8 ld_user_start;
  return &ld_user_start;
}


/* This function acts as the landing  point from machine mode.  It is the *
 * first function run in supervisor mode and configures the system before *
 * creating the first user process.                                       */
void supervisor_start([[gnu::unused]] u32 hartid, [[gnu::unused]] void* device_tree) {
	void user_start(void*);
	initialize();
	
	kprintf("Kernel start: %x\n", &ld_text_start);
	kprintf("--Kernel size: %d\n", (&ld_text_end)-(&ld_text_start));
	kprintf("Globals start: %x\n", &ld_data_start);
	kprintf("Heap/Stack start: %x\n", &ld_mem_start);
	kprintf("--Free Memory Available: %d\n", (&ld_mem_end)-(&ld_mem_start));


	user_start(load_executable("user_run"));
}




/* 
 * NOTE - This implementation of `load_executable` is a placeholder that
 *        simply returns the address of the `ld_user_start` symbol to help
 *        locate the `user_run` function that was placed at that address 
 *        during linking.  It "fakes" the process of reading a program from
 *        disk, placing it in memory, then returning that address for the
 *        kernel to run.
 *
 *        Ideally, we would want to replace this with a real version
 *        eventually.
 */

