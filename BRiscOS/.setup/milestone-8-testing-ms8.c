#ifdef UTEST
#include <blib.h>
#include <testing.h>
REGISTER(INTEGRATION, 8, integration_tests, suites, 1);
//          NAME MS        FUNC  SUITES, NUM_SUITES


static test_suite_t suites[] = {
  {
    .section = "Basic",
    .prompts = {
      "Initializes heap",
    }
  },
};

static override_t* shadows;
[[gnu::unused]]
static i64 spoof_handler([[gnu::unused]] wrapper_alias_e name, ...) {
  return 1;
}

static i64 spoof_shell([[gnu::unused]] wrapper_alias_e name, ...) {
  IF_MS(8, assert(shadows[INIT_HEAP].numcalls == 1, &suites[0].results[0], "s", "FAIL - Heap not initialized before entering shell"));
  return 1;
}

static void integration_tests([[gnu::unused]] override_t* shad) {
  shadows = shad;
  shadows[SHELL].precall = spoof_shell;
  IF_MS(7, shadows[HANDLE_TIMER].precall = spoof_handler);
}

#endif
