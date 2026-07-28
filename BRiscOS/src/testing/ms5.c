#ifdef UTEST

#include <blib.h>
#include <testing.h>
#include <wrapper_defs.h>
#include <queues.h>

//         NAME      MS               FUNC  SUITES, NUM_SUITES
REGISTER(INTEGRATION, 5, integration_tests, suites, 1);

static test_suite_t suites[] = {
  {
    .section = "Initialization",
    .prompts = {
      "Initializes queues"
    }
  }
};

static override_t* shadows;

[[gnu::unused]]
static i64 noop([[gnu::unused]] wrapper_alias_e name, ...) {
  return 1;
}

static i64 enter_userspace([[gnu::unused]] wrapper_alias_e name, ...) {
  assert(shadows[INIT_QUEUES].numcalls == 1, &suites[0].results[0], "s", "FAIL - Queues were not initialized");
  assert(ready_list.sortby == SORT_PRIORITY, &suites[0].results[0], "s", "FAIL - ready_list not set to correct algorithm");
  return 1;
}

static void integration_tests([[gnu::unused]] override_t* shad) {
  shadows = shad;
  shadows[USER_START].precall = enter_userspace;
  IF_MS(6, shadows[HANDLE_TIMER].precall = noop);
}


#endif
