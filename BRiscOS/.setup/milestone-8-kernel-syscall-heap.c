#include <blib.h>
#include <threads.h>
#include <interrupts.h>
#include <config.h>
#include <mutex.h>

#define MAX_BREAK ((u64)get_stack(NUM_THREADS))   /*  Calculate the maximum address usable by the heap  */



static u8* program_break = &ld_mem_start;         /*  Address for the current top of the heap  */
static u32 heap_lock = 0;                         /*  it initially starts at the top of BSS    */


/*  adjust_shared_heap - This helper function calculates a new heap top based on  *
 *                       a request for `size` bytes and returns a pointer to the  *
 *                       newly aquired segment of memory.                         *
 *  @size   - u64    Number of bytes to add to the heap.                          *
 *  @return - void*  Pointer to the newly allocated block of memory.              */
static inline void* adjust_shared_heap(u64 size) {
  u8* result = (u8*)-1;
  u64 max = MAX_BREAK;
  lock_mutex(&heap_lock);                        /*  Lock the heap data structure  */

  if ((u64)program_break + size > max)           /*  Ensure we don't write into stack space  */
    goto finalize_adjust_shared_heap;

  program_break += size;                         /*  Add to the top of the heap, this effectively  */
  result = (void*)(program_break - size);        /*  allocates `size` bytes into the heap          */

 finalize_adjust_shared_heap:
  release_mutex(&heap_lock);
  return (void*)result;
}


/*  kmalloc - The kernel allocation method.   This is used internally by the kernel to  *
 *            dynamically  aquire blocks of memory.   For our simple implementation it  *
 *            shares space with user requests.                                          *
 *  @size   - u64    The number of bytes to allocate to the kernel.                     *
 *  @return - void*  Returns the pointer to the newly aquired block or NULL on failure  */
void* kmalloc(u64 size) {
  u8* result = adjust_shared_heap(size);
  return (result == (u8*)-1 ? NULL : result);
}

/*  extend_heap - The system call interface for a user thread to request a dynamic  *
 *                allocation from the heap.   This function is intended to be used  *
 *                once to allocate a global, userspace heap for the process.        *
 *  @size   - u64    The size of the requsted allocation in bytes.                  *
 *  @return - void*  Returns a pointer to the current top of the heap.              *
 * -------------------------------------------------------------------------------- *
 *     NOTE - The return style means you do not allocate memory using `extend_heap` *
 *            like malloc.  Invoking `extend_heap` with size 0 to find the current  *
 *            space first, before allocating more  can be used to  validate if the  *
 *            request was honored by the kernel.                                    *
 * -------------------------------------------------------------------------------- */
syscall_t extend_heap(u64 size) {
  adjust_shared_heap(size);
  return (i64)program_break;
}
