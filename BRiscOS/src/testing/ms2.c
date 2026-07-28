#ifdef UTEST
#include <blib.h>
#include <io.h>
#include <testing.h>
#include <devices.h>
#include <wrapper_defs.h>

//         NAME MS        FUNC  SUITES, NUM_SUITES
REGISTER(INTEGRATION, 2, integration_tests, suites, 1);

static test_suite_t suites[] = {
  {
    .section = "General",
    .prompts = {
      "User space printf causes writes to UART"
    }
  }
};

static override_t* shadows;
static char test_buffer[5][1024] = { 0 };
static u32  line = 0, ch = 0;

static void user_space_tests(void) {
  printf("This is a test\n");
  assert(test_strcmp(test_buffer[0], "This is a test\n") == 0, &suites[0].results[1], "s", "FAIL - Output to console did not match");
}


/* ---------------------------------------------------------- */

static i64 spoof_stdout([[gnu::unused]] device_t* dev, [[gnu::unused]] u64 handle, const char* buffer, u64 len) {
  for (u32 i=0; i<len; i++) {
    test_buffer[line][ch] = buffer[i];
    if (assert(ch < 1024, &suites[0].results[line+2], "s", "FAIL - Line printed too many characters"))
      ch += 1;
    if (buffer[i] == '\n') {
      if (assert(line < 5, &suites[0].results[line+2], "s", "FAIL - Too many lines printed")) {
	line += 1;
	ch = 0;
      }
    }
  }

  return (i64)len;
}

static i64 enter_userspace([[gnu::unused]] wrapper_alias_e name, ...) {
  void __real_user_start(void (*)(void));
  __real_user_start(user_space_tests);
  return 1;
}

static i64 intercept_console([[gnu::unused]] wrapper_alias_e name, ...) {
  device_t* uart = find_anonymous_device(0x10000000);
  if (!assert(uart != NULL, &suites[0].results[0], "s", "FAIL - UART device could not be found"))
    return 0;

  uart->write = spoof_stdout;
  return 0;
}

static void integration_tests([[gnu::unused]] override_t* shad) {
  shadows = shad;
  shadows[INIT_UART].postcall = intercept_console;
  shadows[USER_START].precall = enter_userspace;
}

#endif
