#include <blib.h>
#include <syscall.h>
#include <io.h>
#include <system.h>
#include <malloc.h>

/*
 *  This file contains our initializer thread, sometimes called the
 *  null thread, init, systemd, or start thread depending on the OS
 *
 *  Regardless of its name, this is the first thread our OS runs once
 *  it finishes kernel initialization and acts as the part to all other
 *  threads/processes
 */


___START___
u8 user_run([[gnu::unused]] char* arg) {
	printf("It finished loading, it will crash soon");
	init_heap(HEAP_SIZE);
	i64 tid=syscall(CREATE, shell, 0, arg, 0);
	syscall(RESUME, tid);

  while (1);
}



/* 
 * NOTE - The ___START___ macro is a hack to send up a flare that allows the kernel to find
 *        the `user_run` function without directly referencing it by name. It would not be 
 *        necessary if/when the kernel is able to load exectuables dynamically from NV sources
 *        since the kernel could choose the address at runtime, instead of at build time.
 *
 *        Leaving it anonymous in this way, however, allows us to build the scaffold
 *        we can use later to implement fully dynamic executable loading.
 */
