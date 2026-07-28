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
void* malloc(u64 alloc_size) {
	if (freelist==NULL)
		return NULL;
	alloc_t *temp=freelist;
	alloc_t *prev=NULL;
	while (temp->size<alloc_size){
		prev=temp;
		temp=temp->next;
		if (temp==NULL)
			return NULL;
	}
	i64 size=(i64) temp->size;
        i64 a_size=(i64) alloc_size;
        i64 size_o=(i64) sizeof(alloc_t);
	u8 *temp_pointer=(u8 *) temp;
	if (temp->size==alloc_size){
		if (prev==NULL)
			freelist=temp->next;
		else 
			prev->next=temp->next;
		temp->size=alloc_size;
	}
	else if (size-a_size-size_o>=1){
		alloc_t *new_node=(alloc_t *) (void *) (temp_pointer+alloc_size+sizeof(alloc_t));
		new_node->size=temp->size-alloc_size-sizeof(alloc_t);
		new_node->next=temp->next;
		if (prev==NULL)
			freelist=new_node;
		else 
			prev->next=new_node;
		temp->size=alloc_size;
	}
	return (alloc_t *) (void *) (temp_pointer+sizeof(alloc_t));
}


/*  free - Frees the memory  associated with the provided address.   This will return  *
 *         the allocation  to the freelist such that all free allocations  are listed  *
 *         in ascending memory order.  If, after freeing the allocation, two adjacent  *
 *         blocks are free, the function coalesces these into a single, large alloc.   *
 *  @addr   - void*  Address of the  allocation to be free.   It is expected that the  *
 *                   memory directly below the address contains an `alloc_t` metadata  *
 *                   block.                                                            *
 *  @return - void                                                                     */
void free(void* addr){
	alloc_t *temp=freelist;
	alloc_t *prev=NULL;
	u8 *addr_ptr=(u8 *) addr;
	alloc_t *new_node= (alloc_t *) (void *) (addr_ptr-sizeof(alloc_t));
	new_node->next=NULL;
	if (freelist==NULL){
		freelist=new_node;
		return;
	}
	while (temp!=NULL && temp<new_node){
		prev=temp;
		temp=temp->next;
	}
	if (prev==NULL){
		freelist=new_node;
		new_node->next=temp;
	}
	else {
		prev->next=new_node;
		new_node->next=temp;
	}
	u8 *new_node_ptr=(u8 *) new_node;
	alloc_t *lower=NULL;
	if (prev!=NULL){
		u8 *prev_ptr=(u8 *) prev;
		lower= (alloc_t *) (void *) (prev_ptr+prev->size+sizeof(alloc_t));
	}
	alloc_t *upper= (alloc_t *) (void *) (new_node_ptr+new_node->size+sizeof(alloc_t));
	if (upper==new_node->next){
		new_node->size+=new_node->next->size+sizeof(alloc_t);
		new_node->next=new_node->next->next;
	}
	if (lower==new_node){
		prev->size+=new_node->size+sizeof(alloc_t);
		prev->next=new_node->next;
	}
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
