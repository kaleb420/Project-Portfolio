#ifndef H_SEM
#define H_SEM

#define S_FREE 0
#define S_USED 1

#define FUTEX_WAIT 0
#define FUTEX_WAKE 1

typedef struct _sem {  /*                                           */
  i64 count;           /*  Semaphore structure  stores the current  */
  u8 state;            /*  state of the semaphore and the resource  */
} semaphore_t;         /*  count  associated  with this  semaphore  */
                       /*                                           */

i32 create_sem(semaphore_t*, i64);
i32 free_sem(semaphore_t*);
i32 wait_sem(semaphore_t*);
i32 post_sem(semaphore_t*);

#endif
