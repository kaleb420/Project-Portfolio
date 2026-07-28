#ifdef UTEST

#include <blib.h>
#include <testing.h>
#include <wrapper_defs.h>

//         NAME MS        FUNC  SUITES, NUM_SUITES
REGISTER(INTEGRATION, 1, integration_tests, suites, 1);

static test_suite_t suites[] = {
  {
    .section = "General",
    .prompts = {
      "UART Initialized",
      "Externals Initialized",
      "Printed Kernel Start",
      "Printed Kernel Size",
      "Printed Data Start",
      "Printed Heap Start",
      "Printed Heap Size"
    }
  }
};

static override_t* shadows;
static char test_buffer[5][1024] = { 0 };
static u32  line = 0, ch = 0;

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

static i64 intercept_console([[gnu::unused]] wrapper_alias_e name, ...) {
  assert(override_device(UART, NULL, spoof_stdout) == 0, &suites[0].results[1], "s", "FAIL - UART initialized to an unexpected name");
  return 0;
}

static i64 validate_test(wrapper_alias_e name, ...) {
  EXPAND_ARGS((u64*,void*), (result,addr));
  char text[1024];

  assert(shadows[INIT_UART].numcalls > 0, &suites[0].results[0], "s", "FAIL - UART initialization was not called");
  assert(shadows[INIT_UART].numcalls < 2, &suites[0].results[0], "s", "FAIL - UART initialized too many times");
  assert(shadows[INIT_EXTERNAL].numcalls > 0, &suites[0].results[1], "s", "FAIL - External initialization was not called");
  assert(shadows[INIT_EXTERNAL].numcalls < 2, &suites[0].results[1], "s", "FAIL - Externals initialized too many times");

  string_builder(text, 1024, "sxs", "Kernel start: ", &ld_text_start, "\n");
  assert(test_strcmp(test_buffer[0], text) == 0, &suites[0].results[2], "s", "FAIL - Output string didn't match specification");

  string_builder(text, 1024, "sds", "--Kernel size: ", &ld_text_end - &ld_text_start, "\n");
  assert(test_strcmp(test_buffer[1], text) == 0, &suites[0].results[3], "s", "FAIL - Output string didn't match specification");

  string_builder(text, 1024, "sxs", "Globals start: ", &ld_data_start, "\n");
  assert(test_strcmp(test_buffer[2], text) == 0, &suites[0].results[4], "s", "FAIL - Output string didn't match specification");

  string_builder(text, 1024, "sxs", "Heap/Stack start: ", &ld_mem_start, "\n");
  assert(test_strcmp(test_buffer[3], text) == 0, &suites[0].results[5], "s", "FAIL - Output string didn't match specification");

  string_builder(text, 1024, "sds", "--Free Memory Available: ", &ld_mem_end - &ld_mem_start, "\n");
  assert(test_strcmp(test_buffer[4], text) == 0, &suites[0].results[6], "s", "FAIL - Output string didn't match specification");
  
  return 1;
}

static void integration_tests([[gnu::unused]] override_t* shad) {
  shadows = shad;
  shadows[INIT_UART].postcall = intercept_console;
  shadows[USER_START].precall = validate_test;
}

#endif
