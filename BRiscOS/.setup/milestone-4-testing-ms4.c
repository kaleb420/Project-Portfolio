#ifdef UTEST

#include <blib.h>
#include <syscall.h>
#include <system.h>
#include <testing.h>
#include <devices.h>
#include <wrapper_defs.h>
#include <actionqueue.h>

//         NAME      MS               FUNC  SUITES, NUM_SUITES
REGISTER(INTEGRATION, 4, integration_tests, suites, 2);

static test_suite_t suites[] = {
  {
    .section = "Initialization",
    .prompts = {
      "Initializes threads"
    }
  },
  {
    .section = "Thread Management",
    .prompts = {
      "Created threads are suspended",
      "Resumed threads execute",
      "Suspended threads do not execute",
      "Joining blocks until completion",
      "Completed threads return value"
    }
  }
};

typedef enum {
  NONE,
  STARTED,
  RESUMED,
  SUSPENDED,
  JOINED,
  COMPLETE,
} states_e;

static override_t* shadows;
static states_e state;
static bool called = false;

static u8 test_thread([[gnu::unused]] char* arg) {
  while (1) {
    called = true;
    switch (state) {
    case NONE:
      assert(0, &suites[1].results[0], "s", "!!ERROR - Thread started while test in unstable state, test suite failure"); break;
    case STARTED:
      assert(0, &suites[1].results[0], "s", "FAIL - Thread executed code before being resumed"); break;
    case RESUMED:
      assert(1, &suites[1].results[1], "s", "!!ERROR - This test cannot fail"); break;
    case SUSPENDED:
      assert(0, &suites[1].results[2], "s", "FAIL - Thread executed after suspend syscall"); break;
    case JOINED:
      syscall(FORCE_RESCHED);
      state = COMPLETE;
      break;
    case COMPLETE:
      return 120;
    }
    syscall(FORCE_RESCHED);
  }
  return 0;
}
  
static void user_space_tests(void) {
  u32 tid;
  called = false;
  tid = (u32)syscall(CREATE, test_thread, 0, NULL, 0);
  state = STARTED;
  syscall(FORCE_RESCHED);
  assert(!called, &suites[1].results[0], "s", "FAIL - Test thread was run before it was resumed");

  called = false;
  state = RESUMED;
  syscall(RESUME, tid);
  syscall(FORCE_RESCHED);
  assert(called, &suites[1].results[1], "s", "FAIL - Thread was not run after it was resumed");

  called = false;
  state = SUSPENDED;
  syscall(SUSPEND, tid);
  syscall(FORCE_RESCHED);
  assert(!called, &suites[1].results[2], "s", "FAIL - Thread run after it was suspended");

  called = false;
  state = RESUMED;
  syscall(RESUME, tid);
  syscall(FORCE_RESCHED);
  assert(called, &suites[1].results[1], "s", "FAIL - Thread was not run after it was resumed");

  for (i32 i=0; i<20; i++)
    syscall(FORCE_RESCHED);

  called = false;
  state = JOINED;
  u8 result = (u8)syscall(JOIN, tid);
  assert(state == COMPLETE, &suites[1].results[3], "s", "FAIL - Function returned from JOIN before thread was complete");
  assert(result == 120, &suites[1].results[4], "sd", "FAIL - Join did not return the correct value.  Expected 120, got ", result);
}

[[gnu::unused]]
static i64 noop([[gnu::unused]] wrapper_alias_e name, ...) {
  return 1;
}

static i64 enter_userspace([[gnu::unused]] wrapper_alias_e name, ...) {
  assert(shadows[INIT_THREADS].numcalls == 1, &suites[0].results[0], "s", "FAIL - Threads not initialized before entering user space");
  void __real_user_start(void (*)(void));
  __real_user_start(user_space_tests);
  return 0;
}

static void integration_tests([[gnu::unused]] override_t* shad) {
  shadows = shad;
  shadows[USER_START].precall = enter_userspace;
  IF_MS(6, shadows[HANDLE_TIMER].precall = noop);
}

#endif
