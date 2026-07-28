#include <blib.h>
#include <threads.h>
#include <queues.h>

static queue_t queue_table[NUM_THREADS];    /*  The table of all queue entries, one for each thread   */

queue_head_t ready_list = {0};              /*  The head node of the list of ready threads            */


/*  list_insert_thread - This function adds a thread to the provided queue by manipulating the  *
 *                       queue_table.                                                           *
 *  @list     - queue_head_t*  Pointer to the list to be modified.                              *
 *  @threadid -           u32  The threadid to add to the list.                                 *
 *  @key      -           i64  Value to use as the key for sorting the queue.                   *
 *  @return   -           i32  The threadid added to the list                                   */
i32 list_insert_thread(queue_head_t* list, u32 threadid, i64 key) {

  return 0;
}


/*  list_dequeue_thread - This function removes the thread at the head of the provided queue and  *
 *                        returns its id.                                                         *
 *  @list     - queue_head_t*  Pointer to the list to be modified.                                *
 *  @return   -           i32  The threadid removed from the list                                 */
i32 list_dequeue_thread(queue_head_t* list) {

  return 0;
}


/*  list_remove_thread - This function removes a specific thread from the providd queue and   *
 *                       returns it.                                                          *
 *  @list     - queue_head_t*  Pointer to the list to be modified.                            *
 *  @threadid -           u32  The threadid to removefrom the list                            *
 *  @return   - i32  The threadid removed from the list                                       */
i32 list_remove_thread(queue_head_t* list, u32 threadid) {

  return 0;
}


/*  init_queues - This function sets up the initial state of the ready_list and queue_table  */
void init_queues(void) {
  
}


/*
 *  NOTE - We won't need 'list_deuqueue_key' until Milestone 7,
 *        where we will need the ability to add add and remove
 *	  abritrary keys on a single list.
 */

/*  list_dequeue_key - Removes the first instance of a given key.  *
 *  @list   - queue_head_t*  Pointer to the list to be modified.   *
 *  @key    -           i64  Key to remove from the list.          *
 *  @return -           i32  The threadid removed from the list.   */
i32 list_dequeue_key([[gnu::unused]] queue_head_t* list, [[gnu::unused]] i64 key) {

  return 0;
}


#include <queues.ut>
#include <queues_delta.ut>
#include <queues_fifo.ut>
