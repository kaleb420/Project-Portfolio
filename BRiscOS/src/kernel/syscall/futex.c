#include <blib.h>
#include <interrupts.h>
#include <futex.h>
#include <queues.h>
#include <threads.h>

/*  futex - This system  call responds to requests by a user  thread to modify the state  *
 *          of a "fast userspace mutex" (futex).   This can  cause the current thread to  *
 *          be suspended, pending another signal to the same futex or for another thread  *
 *          to be resumed, depending on the `op` requested.                               *
 *  @addr   - void*  This address  acts as the unique key to identify the futex.   It is  *
 *                   not used as a pointer while in kernel space (hence the 'u' in futex) *
 *  @op     - u64    Operation requested on the futex.  This can be one of FUTEX_WAIT or  *
 *                   FUTEX_WAKE, corresponding with a request to block the current thread *
 *                   or wake the next `count` waiting threads on that futex.              *
 *  @count  - i64    Meaningful  only in FUTEX_WAKE, this  corresponds to the number  of  *
 *                   threads to awaken from the requested futex.                          *
 *  @return - i64    Returns -1  on failure.   On success,  the result depends on the op  *
 *                   In  FUTEX_WAIT,  returns the  threadid of the  awaited thread.   In  *
 *                   FUTEX_WAKE, returns the number of threads awakened.                  */
syscall_t futex(void* addr, u64 op, i64 count) {
	if (addr==NULL)
		return -1;
	i32 temp=-1;
	if (op==0)
		temp=enqueue_futex(addr, get_current_thread());
	else if (op==1)
		temp=dequeue_futex(addr, (u32) count);
	resched();
	return temp;
}




#include <futex_syscall.ut>
