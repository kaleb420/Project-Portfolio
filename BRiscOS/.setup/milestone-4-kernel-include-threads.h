#ifndef H_THREAD
#define H_THREAD

#include <config.h>
#include <blib.h>

/*
 * This macro contains a list of all possible states that a thread can exist in.
 * Adding new states to this list is sometimes necessary when adding functionality.
 *    (see `shared/include/syscall.h` for a detailed overview of x-macros)
 */
#define THREAD_STATE_LIST(entry) \
  entry(TH_FREE)                 \
  entry(TH_RUNNING)		 \
  entry(TH_READY)		 \
  entry(TH_SUSPEND)		 \
  entry(TH_DEFUNCT)		 \



#define ENUMERATE(x) x,             /*                                 */
typedef enum {                      /*  Use the THREAD_STATE_LIST to   */
  THREAD_STATE_LIST(ENUMERATE)      /*  generate a list enumeration of */
  NUM_STATES                        /*  possible thread states.        */
} threadstate_e;                    /*                                 */
#undef ENUMERATE


#define THREAD_STACK_SZ (u64)(((&ld_mem_end - &ld_mem_start) / 2) / NUM_THREADS)
#define get_stack(n) ((u64)&ld_mem_end - (n * THREAD_STACK_SZ))


/*
 * thread_mem_t holds memory mapping information for a given thread.
 * This information is used by the kernel to isolate that thread's
 * stack when performing work on it and then return to that thread's
 * user stack when it's done.
 */
typedef struct {
  void* kernel_stack;
  void* user_stack;
} thread_mem_t;


/*
 * The thread_t structure holds all of the information used by the kernel
 * to manage a given thread.
 */
typedef struct _thread {
  thread_mem_t  memorymap;     /*  List of important memory locations for the thread   */
  threadstate_e state;         /*  Current state the thread is in i.e. TH_RUNNING      */
  u32           parent;        /*  The thread id of the process that created this one  */
  u8            retval;        /*  The return value of the thread until reaped.        */
  u32           priority;      /*  The thread's priority, used by the scheduler        */
} thread_t;

void init_threads(void);
void resched(void);
void ctxsw(thread_mem_t*, thread_mem_t*);
u32 get_current_thread(void);
u32 set_current_thread(u32);

extern thread_t thread_table[NUM_THREADS];

#endif
