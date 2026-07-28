#ifdef UTEST
#include <blib.h>
#include <testing.h>

REGISTER(INTEGRATION, 6, integration_tests, suites, 1);

static test_suite_t suites[] = {
  {
    .section = "General",
    .prompts = {
      "Timers are initialized",
      "Handler invoked on interrupt"
    }
  }
};


static override_t* shadows;
[[gnu::unused]]
static i64 spoof_handler([[gnu::unused]] wrapper_alias_e name, ...) {
  return 1;
}

[[gnu::unused]]
static i64 noop([[gnu::unused]] wrapper_alias_e name, ...) {
  return 1;
}

static i64 enter_userspace([[gnu::unused]] wrapper_alias_e name, ...) {
  i64 handle_interrupt(i64, u64);
  i64 (*handler)(i64, u64) = handle_interrupt;
  IF_MS(6, assert(shadows[INIT_TIMER].numcalls == 1, &suites[0].results[0], "s", "FAIL - TIMER not initialized before entering user space"));
  handler(0, 0x8000000000000005);
  IF_MS(6, assert(shadows[HANDLE_TIMER].numcalls > 0, &suites[0].results[1], "s", "FAIL - Handler was not called on timer interrupt"));
  IF_MS(6, assert(shadows[HANDLE_TIMER].numcalls < 2, &suites[0].results[1], "s", "FAIL - Handler called too many times on timer interrupt"));
  return 1;
}

static void integration_tests([[gnu::unused]] override_t* shad) {
  shadows = shad;
  shadows[USER_START].precall = enter_userspace;
  IF_MS(6, shadows[HANDLE_TIMER].precall = spoof_handler);
  IF_MS(6, shadows[HANDLE_TIMER].precall = noop);
}


#endif
