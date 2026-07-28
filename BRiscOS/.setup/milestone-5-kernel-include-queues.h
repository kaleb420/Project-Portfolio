#ifndef H_QUEUE
#define H_QUEUE

#include <config.h>
#include <blib.h>

/*
 *  A list of possible sorting algorithms used by the kernel when handling lists of threads
 */
typedef enum {
  SORT_PRIORITY,
  SORT_DELTA,
  SORT_FIFO
} algorithm_e;

/*
 *  This structure contains a node in a linked list used by the kernel to maintain lists of threads
 */
typedef struct _queue {
  i64 key;                         /*  A generic key.  It's meaning depends on the list type  */
  struct _queue* q_prev;           /*  A pointer to the previous node in the list             */
  struct _queue* q_next;           /*  A pointer to the next node in the list                 */
} queue_t;


/*
 *  This struct defines the descriptor for a list of threads.  This entity is used to track the
 *  current state of a list as a while.
 */
typedef struct {
  algorithm_e sortby;          /*  Indicates which algorithm this list uses         */
  u32 head;                    /*  Indicates the first thread contined in the list  */
  i32 count;                   /*  Indicates the number of threads in the list      */
} queue_head_t;

i32 list_insert_thread(queue_head_t*, u32, i64);
i32 list_dequeue_thread(queue_head_t*);
i32 list_dequeue_key(queue_head_t*, i64);
i32 list_remove_thread(queue_head_t*, u32);

void init_queues(void);

extern queue_head_t ready_list;

#endif
