#include <blib.h>
#include <testing.h>
#include <complete.h>
#include <devices.h>
#include <wrapper_defs.h>
#include <printing.h>
#include <guard.h>

override_t test_shadows[NUM_WRAPPERS] = { 0 };

static const u32 timer_interval = 100000;
static volatile u64* clint_timer_addr = ((u64*)0x2004000);
static i32 timeout = -1;

#define ENUMERATE(ms, name, fn, ty, tys, names) ty CAT(__wrap_, fn)(ARGS_LIST(tys, names)) {                                        \
  ty result;                                                                                                                        \
  test_shadows[name].numcalls += 1;                                                                                                 \
  if (test_shadows[name].precall && test_shadows[name].precall(name, NAME_LIST(APPEND_LIST(ty, tys), APPEND_LIST(&result, names)))) \
    return result;						                                                                    \
  IF_MS(ms, ty CAT(__real_, fn)(TYPE_LIST(tys, names)));                                                                            \
  IF_MS(ms, result = CAT(__real_, fn)(NAME_LIST(tys, names)));                                                                      \
  if (test_shadows[name].postcall)                                                                                                  \
    test_shadows[name].postcall(NAME_LIST(APPEND_LIST(wrapper_alias_t, tys), APPEND_LIST(name, names)));                            \
  return result;                                                                                                                    \
}
WRAPPER_LIST(ENUMERATE)
#undef ENUMERATE

[[gnu::interrupt("machine")]]
void __wrap_delegate_clk(void) {
  *clint_timer_addr += timer_interval;
  if (--timeout == 0) {
    print_result("TIMEOUT - The test did not return before a specified timeout", WARN);
    asm volatile("j _boothdr");
  }
}

[[gnu::interrupt("machine")]]
void __wrap_handle_exception(void) {
  u64 cause;
  asm volatile("csrr %0, mcause" : "=r" (cause));
  if (cause == 0x9)
    asm volatile("li a0, 0x20\n"
		 "csrc mip, a0");
  else {
    if (guard_point.registered)
      restore_guard(1);
    print_result("UNKNOWN - The test could not be completed due a premature return or missing code", WARN);
    asm volatile("j _boothdr");
  }
}

#if MILESTONE_2 == 1
i64 __wrap_handle_syscall(i64 idx, i64 a1, i64 a2, i64 a3, i64 a4) {
  if (idx == FORCE_RESCHED) {
    IF_MS(4, resched());
  }
  else {
    i64 __real_handle_syscall(i64, i64, i64, i64, i64);
    return __real_handle_syscall(idx, a1, a2, a3, a4);
  }
  return 0;
}
#endif

void __wrap_supervisor_start([[gnu::unused]] u32 hartid, [[gnu::unused]] void* devtree) {
  run_unit_tests(&timeout);

  u64 result;
  void __real_supervisor_start(u32, void*);
  if (!test_shadows[SUPERVISOR_START].precall || !test_shadows[SUPERVISOR_START].precall(SUPERVISOR_START, &result, hartid, devtree))
    __real_supervisor_start(0, NULL);
}
