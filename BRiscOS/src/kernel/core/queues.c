#include <blib.h>
#include <threads.h>
#include <queues.h>
#include <sleep.h>

static queue_t queue_table[NUM_THREADS];    /*  The table of all queue entries, one for each thread   */

queue_head_t ready_list = {0};              /*  The head node of the list of ready threads            */

int contains_thread(queue_head_t *list, u32 threadid){
	if (list==NULL || list->count==0 || threadid>=NUM_THREADS)
		return 0;
	queue_t *temp=&queue_table[list->head];
	int count=0;
	while (count!=list->count){
		if (temp==&queue_table[threadid])
			return 1;
		temp=temp->q_next;
		count++;
	}
	return 0;
}

u32 get_next_head(queue_head_t *list){
	queue_t *next_node=queue_table[list->head].q_next;
	for (u32 i=0; i<NUM_THREADS; i++){
		if (&queue_table[i]==next_node)
			return i;
	}
	return 0;
}

i32 insert_logic(queue_head_t *list, u32 threadid, queue_t *inserted_node, queue_t *curr_node, int start, int end){
	if (start==1){
		inserted_node->q_next=curr_node;
		inserted_node->q_prev=curr_node->q_prev;
		curr_node->q_prev->q_next=inserted_node;
		curr_node->q_prev=inserted_node;
		list->head=threadid;
	}
	else if (end==1){
                inserted_node->q_next=&queue_table[list->head];
                inserted_node->q_prev=curr_node;
                queue_table[list->head].q_prev=inserted_node;
                curr_node->q_next=inserted_node;
        }
	else {
		inserted_node->q_next=curr_node;
		inserted_node->q_prev=curr_node->q_prev;
		curr_node->q_prev->q_next=inserted_node;
		curr_node->q_prev=inserted_node;
	}
	list->count++;
	return (i32) threadid;
}

/*  list_insert_thread - This function adds a thread to the provided queue by manipulating the  *
 *                       queue_table.                                                           *
 *  @list     - queue_head_t*  Pointer to the list to be modified.                              *
 *  @threadid -           u32  The threadid to add to the list.                                 *
 *  @key      -           i64  Value to use as the key for sorting the queue.                   *
 *  @return   -           i32  The threadid added to the list                                   */
i32 list_insert_thread(queue_head_t* list, u32 threadid, i64 key) {
	if (contains_thread(list, threadid)==1 || threadid>=NUM_THREADS)
		return -1;
	queue_t *inserted_node=&queue_table[threadid];
        inserted_node->key=key;
	if (list->count==0) {
		inserted_node->q_next=inserted_node;
		inserted_node->q_prev=inserted_node;
		list->head=threadid;
		list->count++;
		return (i32) threadid;
	}
	queue_t *curr_node=&queue_table[list->head];
	int start=1;
	int end=0;
	if (list->sortby==SORT_DELTA){
		i64 temp_key=key;
		while (temp_key>=curr_node->key){
			start=0;
			temp_key-=curr_node->key;
			if (curr_node->q_next==&queue_table[list->head] || list->count==1){
				end=1;
				break;
			}
			curr_node=curr_node->q_next;
		}
		if (end!=1)
			curr_node->key-=temp_key;
		inserted_node->key=temp_key;
	}
	else if (list->sortby==SORT_PRIORITY){
        	while (key>=curr_node->key){
        	        start=0;
        	        if (curr_node->key>=curr_node->q_next->key || list->count==1){
        	                end=1;
        	                break;
        	        }
        	        curr_node=curr_node->q_next;
        	}
	}
	else if (list->sortby==SORT_FIFO){
		while (curr_node->q_next!=&queue_table[list->head]){
			curr_node=curr_node->q_next;
		}
		end=1;
		start=0;
	}
	return insert_logic(list, threadid, inserted_node, curr_node, start, end);
}

i32 dequeue_logic(queue_head_t *list, u32 threadid){
        queue_t *node=&queue_table[threadid];
        if (list->count==1){
		list->head=0;
        }
	else {
        	list->head=get_next_head(list);
        	node->q_next->q_prev=node->q_prev;
        	node->q_prev->q_next=node->q_next;
	}
        node->q_next=NULL;
        node->q_prev=NULL;
	node->key=0;
        node=NULL;
        list->count--;
        return (i32) threadid;
}


/*  list_dequeue_thread - This function removes the thread at the head of the provided queue and  *
 *                        returns its id.                                                         *
 *  @list     - queue_head_t*  Pointer to the list to be modified.                                *
 *  @return   -           i32  The threadid removed from the list                                 */
i32 list_dequeue_thread(queue_head_t* list) {
	u32 threadid=list->head;
        if (contains_thread(list, threadid)==0 || threadid>=NUM_THREADS || list->count==0)
                return -1;
	if (list->sortby==SORT_DELTA){
		queue_t *h=&queue_table[threadid];
		if (h->key>0){
			h->key-=10;
			return -1;
		}
		else {
			h->q_next->key+=h->key;
		}
	}
	return dequeue_logic(list, threadid);
}

i32 remove_logic(queue_head_t* list, u32 threadid){
	queue_t *traverse=&queue_table[list->head];
        int count=0;
        while (traverse!=&queue_table[threadid]){
                traverse=traverse->q_next;
                count++;
        }
        if (count==0)
                return dequeue_logic(list, threadid);
        else {
                traverse->q_prev->q_next=traverse->q_next;
                traverse->q_next->q_prev=traverse->q_prev;
                traverse->q_prev=NULL;
                traverse->q_next=NULL;
		traverse->key=0;
		traverse=NULL;
        }
	list->count--;
        return (i32) threadid;
}

/*  list_remove_thread - This function removes a specific thread from the providd queue and   *
 *                       returns it.                                                          *
 *  @list     - queue_head_t*  Pointer to the list to be modified.                            *
 *  @threadid -           u32  The threadid to removefrom the list                            *
 *  @return   - i32  The threadid removed from the list                                       */
i32 list_remove_thread(queue_head_t* list, u32 threadid) {
	if (contains_thread(list, threadid)==0 || threadid>=NUM_THREADS || list->count==0)
		return -1;
	if (list->sortby==SORT_DELTA){
		queue_t *h=&queue_table[threadid];
		if (h->q_next!=&queue_table[list->head])
			h->q_next->key+=h->key;
	}
	return remove_logic(list, threadid);
}

/*  init_queues - This function sets up the initial state of the ready_list and queue_table  */
void init_queues(void) {
	for (int i=0; i<NUM_THREADS; i++){
		queue_table[i].q_next=NULL;
		queue_table[i].q_prev=NULL;
		queue_table[i].key=0;
		queue_table[sleep_list.head].q_next=NULL;
		queue_table[sleep_list.head].q_prev=NULL;
		queue_table[sleep_list.head].key=0;
	}
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
i32 list_dequeue_key([[gnu::unused]] queue_head_t* list, [[gnu::unused]] i64 r_key) {
	 if (list->count==0)
                return -1;
        queue_t *node=&queue_table[list->head];
        i32 enter=0;
        while (node!=&queue_table[list->head] || enter==0){
                if (node->key==(i64) r_key)
                        return list_remove_thread(list, (u32) (node-queue_table));
                enter=1;
                node=node->q_next;
        }
        return -1;
}


#include <queues.ut>
#include <queues_delta.ut>
#include <queues_fifo.ut>
