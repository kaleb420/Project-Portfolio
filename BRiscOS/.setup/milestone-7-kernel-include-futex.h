#ifndef H_KERNEL_SEM
#define H_KERNEL_SEM

#define FUTEX_WAIT 0  /*  These macros are used to represent the request type  */
#define FUTEX_WAKE 1  /*  passed to  the futex through the FUTEX  system call  */

void init_futex(void);
i32 enqueue_futex(void*, u32);
i32 dequeue_futex(void*, u32);

#endif
