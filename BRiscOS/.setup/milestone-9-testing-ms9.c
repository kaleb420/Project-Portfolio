#ifdef UTEST
#include <blib.h>
#include <tty.h>
#include <testing.h>
REGISTER(INTEGRATION, 9, integration_tests, suites, 1);
//          NAME MS        FUNC  SUITES, NUM_SUITES


static test_suite_t suites[] = {
  {
    .section = "Basic",
    .prompts = {
      "Initializes tty",
    }
  },
};

static override_t* shadows;
[[gnu::unused]]
static i64 spoof_handler([[gnu::unused]] wrapper_alias_e name, ...) {
  return 1;
}

[[gnu::unused]]
static i64 spoof_tty([[gnu::unused]] wrapper_alias_e name, ...) {
  EXPAND_ARGS((i32*, char*, u64, u32), (result, namespace, uarthandle, flags));
  u64 i=0;
  for (; i<NUM_DEVS; i++)
    if (get_device_by_handle(i)->addr == (u8*)0x10000000)
      break;
  assert(uarthandle == i, &suites[0].results[0], "s", "FAIL - UART handles does not match a device associated with the UART address");
  assert(flags & TTY_ECHO, &suites[0].results[0], "s", "FAIL - Flags did not include TTY_ECHO");
  return 0;
}

static i64 enter_userspace([[gnu::unused]] wrapper_alias_e name, ...) {
  IF_MS(9, assert(shadows[INIT_TTY].numcalls == 1, &suites[0].results[0], "s", "FAIL - TTY not initialized before entering user space"));
  return 1;
}

static void integration_tests([[gnu::unused]] override_t* shad) {
  shadows = shad;
  shadows[USER_START].precall = enter_userspace;
  IF_MS(7, shadows[HANDLE_TIMER].precall = spoof_handler);
  IF_MS(7, shadows[INIT_TTY].precall = spoof_tty);
}

#endif
