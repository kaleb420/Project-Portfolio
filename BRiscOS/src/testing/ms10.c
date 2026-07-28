#ifdef UTEST
#include <blib.h>
#include <testing.h>
REGISTER(INTEGRATION, 10, integration_tests, suites, 1);
//          NAME MS        FUNC  SUITES, NUM_SUITES


static test_suite_t suites[] = {
  {
    .section = "Basic",
    .prompts = {
      "Initializes ramdisk",
      "Initializes filesystem"
    }
  },
};

static override_t* shadows;
[[gnu::unused]]
static i64 spoof_handler([[gnu::unused]] wrapper_alias_e name, ...) {
  return 1;
}

static i64 enter_userspace([[gnu::unused]] wrapper_alias_e name, ...) {
  IF_MS(10, assert(shadows[INIT_RAMDISK].numcalls == 1, &suites[0].results[0], "s", "FAIL - Ramdisk not initialized before entering shell"));
  IF_MS(10, assert(shadows[INIT_FS].numcalls == 1, &suites[0].results[1], "s", "FAIL - Filesystem not initialized before entering shell"));
  return 1;
}

static void integration_tests([[gnu::unused]] override_t* shad) {
  shadows = shad;
  shadows[USER_START].precall = enter_userspace;
  IF_MS(7, shadows[HANDLE_TIMER].precall = spoof_handler);
}

#endif
