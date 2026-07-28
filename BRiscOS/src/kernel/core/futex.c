#include <blib.h>
#include <config.h>
#include <queues.h>
#include <mutex.h>
#include <threads.h>

/* --------------------------------------------------------------------- *
 * NOTE - Hashmaps and Virtual Memory                                    *
 *   The fast hash we use here works for our shared memory space model,  *
 *   but would need to be extended if full virtual memory was added.     *
 * --------------------------------------------------------------------- */

#define HASH(x) (u32)((((u64)x >> 2) + ((u64)x & 0x3)) % NUM_HASHTAB_BINS)

static queue_head_t futex_hashtab[NUM_HASHTAB_BINS];   /*  A list of queue entries that serves as the roots for our hashmap bins  */


/*  enqueue_futex - This function  allows you to add a thread to a  given futex.  Each  *
 *                  futex is linked to an address in  memory and uses the hash of that  *
 *                  address to determine which  hashmap bin (list) to play the thread.  *
 *  @addr     - void*  The physical address of the futex.  For all practical purposes,  *
 *                     this address is just used as a key to identify the futex.        *
 *  @threadid - u32    The thread to be added to the futex queue.                       *
 *  @return   - i32    The thread added to the queue or -1 on failure                   */
i32 enqueue_futex(void* addr, u32 threadid) {
	u32 bin=HASH(addr);
	if (threadid>=NUM_THREADS)
		return -1;
	lock_mutex(&bin);
	i32 temp=list_insert_thread(&futex_hashtab[bin], threadid, thread_table[threadid].priority);
	thread_table[threadid].state=TH_WAITING;
	release_mutex(&bin);
	if (temp==-1)
		return -1;
	return temp;
}


/*  dequeue_futex - This function removes `count` threads  from the bin associated with  *
 *                  the `addr` that have a key matching the hash of `addr`.              *
 *  @addr    - void*  The physical  address of the futex.   For all practical  purposes  *
 *                    this address is just used as a key to identify the futex.          *
 *  @count   - u32    The number of threads to remove from the corresponding list.       *
 *  @return  - i32    The number of threads successfully removed from the list or -1 on  *
 *                    failure.                                                           */
i32 dequeue_futex(void* addr, u32 count) {
	u32 bin=HASH(addr);
	u32 ret=0;
	lock_mutex(&bin);
	while (ret<count){
		i32 threadid=list_dequeue_key(&futex_hashtab[bin], (i64) addr);
		if (threadid!=-1){
			thread_table[threadid].state=TH_READY;
			list_insert_thread(&ready_list, (u32) threadid, thread_table[threadid].priority);
		}
		ret++;
	}
	release_mutex(&bin);
	if (ret==0)
		return -1;
	return (i32) ret; 
}


/*  init_futex - Sets up the futex_hashmap with empty list head nodes in each slot  *
 *  @return - i64  Returns 0 on success or -1 on failure.                           */
void init_futex(void) {
	for (int i=0; i<NUM_HASHTAB_BINS; i++){
		futex_hashtab[i].head=0;
		futex_hashtab[i].count=0;
	}
}



#include <futex.ut>
