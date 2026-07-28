#ifndef H_SYSCALL
#define H_SYSCALL

#include <blib.h>

/* MS3,MS5 - The list below represents the  canonical list of all system  *
 *           calls. Whenever a new system call is required, it should be  *
 *           added to this list using the same template below:            *
 *               entry(syscall_name, function_name) \                     *
 *           As a macro, the trailing backslash in  necessary to allow a  *
 *           multiple line macro declaration.                             */

/* Each ENTRY is a (name, function) tuple             *
 *   `name` is used  to refer to the system call when *
 *          using the `syscall` function in user mode *
 *   `function` refers to the actual function invoked *
 *              by the kernel.                        */
#define SYSCALL_LIST(entry)			\
  entry(OPEN, open)				\
  entry(CLOSE, close)				\
  entry(READ, read)                             \
  entry(WRITE, write)                           \
  entry(CREATE, create)                         \
  entry(KILL, kill)                             \
  entry(SUSPEND, suspend)                       \
  entry(RESUME, resume)                         \
  entry(JOIN, join)                             \
  entry(SLEEP, sleep)                           \
  entry(UNSLEEP, unsleep)                       \
  entry(FUTEX, futex)                           \
  entry(EXTEND_HEAP, extend_heap)               \





/* The following  block of macros provides  the magic glue that automatically *
 * generates a list of system call functions in the  same order with the same *
 * indices whenever it's referenced.  For student code, you should never need *
 * to use this macro, but it is used in template code for you at times.       */

#define ENUMERATE(name, fn) name,
typedef enum {
  SYSCALL_LIST(ENUMERATE)
  NUM_SYSCALLS
} syscall_e;
#undef ENUMERATE

#endif
