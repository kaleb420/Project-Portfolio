#ifndef WRAPPER_DEF_H
#define WRAPPER_DEF_H

#include <complete.h>
#include <macrotools.h>

#ifndef USERSPACE
#if MILESTONE_4 == 1
#include <threads.h>
#endif

#if MILESTONE_5 == 1
#include <queues.h>
#endif
#endif

#if MILESTONE_4 == 0
typedef struct {
  i32 _;
} thread_mem_t;
#endif

#if MILESTONE_5 == 0
typedef struct {
  i32 _;
} queue_head_t;
#endif

#define WRAPPER_LIST(entry)						                                                     \
  entry(1, INIT_UART,            init_uart,                  i64,                  (const char*),               (namespace)) \
  entry(1, USER_START,           user_start,                 u64,                        (void*),                    (addr)) \
  entry(1, INIT_EXTERNAL,        init_external,              i64,                             (),                        ()) \
  entry(1, GET_DEVICE_BY_NAME,   get_device_by_name,   device_t*,                  (const char*),               (namespace)) \
  entry(1, GET_DEVICE_BY_HANDLE, get_device_by_handle, device_t*,                          (u64),                  (handle)) \
  entry(3, SHELL,                shell,                       u8,                  (const char*),                 (cmdline)) \
  entry(3, HELLO,                hello,                       u8,                  (const char*),                 (cmdline)) \
  entry(3, ECHO,                 echo,                        u8,                  (const char*),                 (cmdline)) \
  entry(4, GET_CURRENT_THREAD,   get_current_thread,         u32,                             (),                        ()) \
  entry(4, SET_CURRENT_THREAD,   set_current_thread,         u32,                          (u32),                (threadid)) \
  entry(4, CTXSW,                ctxsw,                      i64, (thread_mem_t*, thread_mem_t*),                (new, old)) \
  entry(4, RESCHED,              resched,                    i64,                             (),                        ()) \
  entry(4, SYSCALL_JOIN,         join,                       i64,                          (u32),                (threadid)) \
  entry(4, SYSCALL_RESUME,       resume,                     i64,                          (u32),                (threadid)) \
  entry(4, SYSCALL_CREATE,       create,                     i64,       (void*, u16, char*, u32), (proc, prio, arg, arglen)) \
  entry(4, INIT_THREADS,         init_threads,               i64,                             (),                        ()) \
  entry(5, LIST_INSERT_THREAD,   list_insert_thread,         i32,      (queue_head_t*, u32, i64),     (list, threadid, key)) \
  entry(5, LIST_DEQUEUE_THREAD,  list_dequeue_thread,        i32,                (queue_head_t*),                    (list)) \
  entry(5, LIST_REMOVE_THREAD,   list_remove_thread,         i32,           (queue_head_t*, u32),          (list, threadid)) \
  entry(5, LIST_DEQUEUE_KEY,     list_dequeue_key,           i32,           (queue_head_t*, i64),               (list, key)) \
  entry(5, INIT_QUEUES,          init_queues,                i64,                             (),                        ()) \
  entry(6, HANDLE_TIMER,         handle_timer,               i64,                             (),                        ()) \
  entry(6, INIT_TIMER,           init_timer,                 i64,                             (),                        ()) \
  entry(7, ENQUEUE_FUTEX,        enqueue_futex,              i32,                   (void*, u32),          (addr, threadid)) \
  entry(7, DEQUEUE_FUTEX,        dequeue_futex,              i32,                   (void*, u32),             (addr, count)) \
  entry(7, LOCK_MUTEX,           lock_mutex,                 i64,                         (u32*),                   (mutex)) \
  entry(7, RELEASE_MUTEX,        release_mutex,              i64,                         (u32*),                   (mutex)) \
  entry(7, INIT_FUTEX,           init_futex,                 i64,                             (),                        ()) \
  entry(8, INIT_HEAP,            init_heap,                  i64,                          (u64),                    (size)) \
  entry(9, INIT_TTY,             init_tty,                   i64,         (const char*, u64, u8),      (ns, uarthdl, flags)) \
  entry(10, INIT_RAMDISK,        init_ramdisk,               i64,             (const char*, u32),                (ns, size)) \
  entry(10, INIT_FS,             init_fs,                    i64,             (const char*, u64),             (ns, diskhdl)) \

i64 init_ramdisk(const char*, u32);
i64 init_fs(const char*, u64);

#define IF_MS(ms, x) CAT(IS_, CAT(MILESTONE_, ms))(x)
#define IS_0(...)
#define IS_1(...) __VA_ARGS__

#define COMMA(x, y) x, y
#define SCOLON(x, y) x; y

#define TYPE(tys, names) LIST_HEAD tys
#define NAME(tys, names) LIST_HEAD names
#define ARG_PAIR(tys, names)  LIST_HEAD tys LIST_HEAD names

#define TYPE_LIST(tys, names) EXPAND(TYPE, COMMA, tys, names)
#define NAME_LIST(tys, names) EXPAND(NAME, COMMA, tys, names)
#define ARGS_LIST(tys, names) EXPAND(ARG_PAIR, COMMA, tys, names)

#define DECL_PAIR(tys, names) [[gnu::unused]] LIST_HEAD tys LIST_HEAD names = va_arg(args, LIST_HEAD tys)
#define VAR_DECLS(tys, names) EXPAND(DECL_PAIR, SCOLON, tys, names)

#define EXPAND_ARGS(tys, names)                 \
  va_list args; va_start(args, name);           \
  VAR_DECLS(tys, names);                        \
  va_end(args)

#define ENUMERATE(ms, name, fn, ...) name,
typedef enum {
  WRAPPER_LIST(ENUMERATE)
  SUPERVISOR_START,
  DEVICE_IN,
  DEVICE_OUT,
  DEVICE_OPEN,
  DEVICE_CLOSE,
  NUM_WRAPPERS,
} wrapper_alias_e;
#undef ENUMERATE

#endif
