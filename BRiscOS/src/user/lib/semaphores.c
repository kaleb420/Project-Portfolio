#include <blib.h>
#include <syscall.h>
#include <semaphores.h>
#include <mutex.h>


/*  create_sem - Initializes a semaphore to represent an specified  *
 *               resource count.                                    *
 *  @sem    - semaphore_t*  Address of the semaphore to be created  *
 *  @count  - i64           The number of resources this semaphore  *
 *                          maintains.                              *
 *  @return - i32           Returns 0 on success, -1 on failure.    */
i32 create_sem([[gnu::unused]] semaphore_t* sem, [[gnu::unused]] i64 count) {
  
  return 0;
}


/*  free_sem - Releases any awaiting threads to prevent leaks.    *
 *  @sem    - semaphore_t*   The semaphore to be freed.           *
 *  @return - i32            Returns 0 on succes, -1 on failure.  */
i32 free_sem([[gnu::unused]] semaphore_t* sem) {
  
  return 0;
}

/*  wait_sem - If any resources, represented by the semaphore count  *
 *             are available, decrement the semaphore, then return,  *
 *             Otherwise,  use the FUTEX  interface to block  until  *
 *             resources are made ready with `post_sem`.             *
 *  @sem    - semaphore_t*  Pointer to the semaphore to await.       *
 *  @return - i32           Returns 0 on success, -1 on failure.    */
i32 wait_sem([[gnu::unused]] semaphore_t* sem) {
  
  return 0;
}


/*  post_sem - Increment the semaphore and release an awaiting thread  *
 *             if the semaphore's  count was less than 0,  indicating  *
 *             the presence of awaiting threads.                       *
 *  @sem    - sempahore_t*  Pointer to the semaphore to notify.        *
 *  @return - i32           Returns the awakened thread id on success  *
 *                          or -1 on failure.                          */
i32 post_sem([[gnu::unused]] semaphore_t* sem) {
  
  return 0;
}
