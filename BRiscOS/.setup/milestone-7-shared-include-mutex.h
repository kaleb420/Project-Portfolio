#ifndef H_MUTEX
#define H_MUTEX

void create_mutex(u32*);     /*  Atomically set the initial mutex value to 0  */
void lock_mutex(u32*);       /*  Atomically set the mutex to 0 if 1, or block */
void release_mutex(u32*);    /*  Atomically set the mutex to 1                */

#endif
