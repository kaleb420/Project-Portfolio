#ifndef H_MALLOC
#define H_MALLOC

#define HEAP_SIZE (((u64)(&ld_mem_end - &ld_mem_start) >> 3) * 3)  /*    This should be approx 1/4 the total rw    */
                                                                   /*    available.                                */

typedef struct _alloc {            /*                                         */
  u64 size;                        /* Memory allocation structure includes    */
  struct _alloc* next;             /* the size of the alloc and a pointer to  */
} alloc_t;                         /* the next allocation (if free)           */
                                   /*                                         */
i32 init_heap(u64);
void* malloc(u64);
void free(void*);

#endif
