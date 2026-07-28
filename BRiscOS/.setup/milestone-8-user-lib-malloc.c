#include <blib.h>
#include <system.h>
#include <malloc.h>

static alloc_t* freelist;   /*  A pointer to the first free block available in the heap  */


/*  malloc - Locates a free block within the `freelist`  that is sufficiently large  *
 *           to fit the requested `size`.   Once located,  the function will remove  *
 *           the allocation from the block and adjust the `freelist` to reflect the  *
 *           new block size.   This may require removing blocks which are no longer  *
 *           large enough to fit new allocations.                                    *
 *  @size   - The number of bytes requested for the new allocation.                  *
 *  @return - Returns a  pointer to the first byte  in the new allocation after the  *
 *            block's metadata.  Returns NULL if no blocks satisfy the request.      */
void* malloc(u64 size) {


  return 0;
}


/*  free - Frees the memory  associated with the provided address.   This will return  *
 *         the allocation  to the freelist such that all free allocations  are listed  *
 *         in ascending memory order.  If, after freeing the allocation, two adjacent  *
 *         blocks are free, the function coalesces these into a single, large alloc.   *
 *  @addr   - void*  Address of the  allocation to be free.   It is expected that the  *
 *                   memory directly below the address contains an `alloc_t` metadata  *
 *                   block.                                                            *
 *  @return - void                                                                     */
void free(void* addr) {


  return;
}



/*  init_heap - Called when the system thread starts in order to generate a user heap.  *
 *              This function initializes the freelist and sets it up for allocations.  *
 *  @size   - u64  The  size of the heap.   In theory, this  could be called  multiple  *
 *                 times to create a multi-heap.  However,  we use it once in OS for a  *
 *                 single, shared heap.                                                 *
 *  @return - i32  Returns 0 on success, -1 on failure.                                 */
i32 init_heap(u64 size) {
  freelist = (alloc_t*)syscall(EXTEND_HEAP, 0);
  if (syscall(EXTEND_HEAP, size) == (i64)freelist)
    return -1;
  freelist->size = size - sizeof(alloc_t);
  freelist->next = NULL;
  return 0;
}



#include <malloc.ut>
