#ifdef UTEST

#include <blib.h>
#include <io.h>
#include <testing.h>
#include <devices.h>
#include <wrapper_defs.h>
#include <actionqueue.h>

//         NAME      MS               FUNC  SUITES, NUM_SUITES
REGISTER(INTEGRATION, 3, integration_tests, suites, 3);

static test_suite_t suites[] = {
  {
    .section = "Echo",
    .prompts = {
      "Returns 0 with arguments",
      "Echos arguments",
      "Returns character count on console read",
      "Echoes each line read from console",
      "Ends on empty line"
    }
  },
  {
    .section = "Hello",
    .prompts = {
      "Returns Error on missing argument",
      "Prints Error on missing argument",
      "Returns zero on success",
      "Prints correct text on success"
    }
  },
  {
    .section = "Shell",
    .prompts = {
      "Prints prompt before command",
      "Loops on no command",
      "Handles unknown command",
      "Calls function based on input",
      "Passes arguments to command",
      "Performs variable subtitution"
    }
  },
};

typedef struct {
  wrapper_alias_e action;
  const char* text;
  u64 len;
  i32 suite;
  i32 test;
} io_op_t;

static override_t* shadows;
static io_op_t io_queue[30];
static u64 io_count = 0, io_idx = 0, io_ele_head = 0;

static void reset_test_io(void) {
  io_count = 0;
  io_idx = 0;
  io_ele_head = 0;
}

static void append_io_op(i32 s, i32 t, wrapper_alias_e action, const char* text) {
  memcpy(&io_queue[io_count++], &(io_op_t) { .action=action, .text=text, .len=(u64)test_strlen(text), .suite=s, .test=t }, sizeof(io_op_t));
}

static bool called_hello = false;
static i64 spoof_hello(wrapper_alias_e name, ...) {
  EXPAND_ARGS((u8*,const char*), (result,cmdline));
  called_hello = true;
  assert(test_strcmp(cmdline, "hello A") == 0, &suites[2].results[4], "s", "FAIL - Incorrect text passed to application function");
  assert(1, &suites[2].results[3], "s", "");
  
  reset_test_io();
  append_io_op(0, 5, DEVICE_OUT, "shell$ ");

#if MILESTONE_9 == 1
  append_io_op(0, 5, DEVICE_IN, "echo T $?  \n");
#else
  append_io_op(0, 5, DEVICE_IN,  "e");
  append_io_op(0, 5, DEVICE_OUT, "e");
  append_io_op(0, 5, DEVICE_IN,  "c");
  append_io_op(0, 5, DEVICE_OUT, "c");
  append_io_op(0, 5, DEVICE_IN,  "h");
  append_io_op(0, 5, DEVICE_OUT, "h");
  append_io_op(0, 5, DEVICE_IN,  "o");
  append_io_op(0, 5, DEVICE_OUT, "o");
  append_io_op(0, 5, DEVICE_IN,  " ");
  append_io_op(0, 5, DEVICE_OUT, " ");
  append_io_op(0, 5, DEVICE_IN,  "T");
  append_io_op(0, 5, DEVICE_OUT, "T");
  append_io_op(0, 5, DEVICE_IN,  " ");
  append_io_op(0, 5, DEVICE_OUT, " ");
  append_io_op(0, 5, DEVICE_IN,  "$");
  append_io_op(0, 5, DEVICE_OUT, "$");
  append_io_op(0, 5, DEVICE_IN,  "?");
  append_io_op(0, 5, DEVICE_OUT, "?");
  append_io_op(0, 5, DEVICE_IN,  " ");
  append_io_op(0, 5, DEVICE_OUT, " ");
  append_io_op(0, 5, DEVICE_IN,  " ");
  append_io_op(0, 5, DEVICE_OUT, " ");
  append_io_op(0, 5, DEVICE_IN, "\n");
  append_io_op(0, 5, DEVICE_OUT, "\n");
#endif
  
  *result = 47;
  return 1;
}

static i64 spoof_echo(wrapper_alias_e name, ...) {
  EXPAND_ARGS((u64*,const char*), (result,cmdline));
  assert(called_hello, &suites[2].results[3], "s", "FAIL - Not all functions correctly invoked by shell");
  assert(test_strcmp(cmdline, "echo T 47  ") == 0, &suites[2].results[5], "ss", "FAIL - Text substitution got incorrect value: ", &cmdline[7]);
  return 1;
}

static void* spoof_read([[gnu::unused]] wrapper_alias_e fn, va_list args, [[gnu::unused]] u64* ctxlen) {
  [[gnu::unused]] device_t* dev = va_arg(args, device_t*);
  [[gnu::unused]] u64 handle = va_arg(args, u64);
  char* buffer = va_arg(args, char*);
  u64 i=0, len = va_arg(args, u64);
  i32 s = io_queue[io_idx].suite, t = io_queue[io_idx].test;

  if (io_count <= io_idx) {
    io_idx += 1;
    goto finalize_spoof_read;
  }
  for (i=0; i<len && i<io_queue[io_idx].len; i++) {
    if (assert(io_queue[io_idx].action == DEVICE_IN, &suites[s].results[t], "s", "FAIL - Expected device write, got device read"))
      buffer[i] = io_queue[io_idx].text[io_ele_head++];
  }
  if (io_queue[io_idx].len <= io_ele_head) {
    io_idx += 1;
    io_ele_head = 0;
  }
 finalize_spoof_read:
  *ctxlen = sizeof(i64);
  return (void*)i;
}

static void* spoof_write([[gnu::unused]] wrapper_alias_e fn, va_list args, [[gnu::unused]] u64* ctxlen) {
  [[gnu::unused]] device_t* dev = va_arg(args, device_t*);
  [[gnu::unused]] u64 handle = va_arg(args, u64);
  const char* buffer = va_arg(args, char*);
  u64 i, len = va_arg(args, u64);
  i32 s = io_queue[io_idx].suite, t = io_queue[io_idx].test;

  if (io_count <= io_idx) {
    io_idx += 1;
    goto finalize_spoof_write;
  }
  for (i=0; i<len; i++) {
    if (assert(io_queue[io_idx].action == DEVICE_OUT, &suites[s].results[t], "s", "FAIL - Expected device read, got device write")) {
      char ch = io_queue[io_idx].text[io_ele_head++];
      if (assert(ch != '\0', &suites[s].results[t], "s", "FAIL - Wrote characters after expected string"))
	assert(ch == buffer[i], &suites[s].results[t], "scscs",
	       "FAIL - Text write to buffer did not match, got '", buffer[i], "', expected '", ch, "'");
      else
	break;
    }
  }
  if (io_queue[io_idx].len <= io_ele_head) {
    io_idx += 1;
    io_ele_head = 0;
  }
 finalize_spoof_write:
  *ctxlen = sizeof(i64);
  return (void*)i;
}

static void echo_tests(void) {
  u8 echo(const char*);
  u8 result;
  register_device_action(DEVICE_IN, spoof_read, NULL);
  register_device_action(DEVICE_OUT, spoof_write, NULL);

  reset_test_io();
  append_io_op(0, 1, DEVICE_OUT, "this is a test string\n");
  result = echo("echo this is a test string");
  assert(result == 0, &suites[0].results[0], "sd", "FAIL - Expected return 0, got ", result);
  assert(io_idx > 0, &suites[0].results[1], "s", "FAIL - Write to console operation was not performed");
  assert(io_idx < 2, &suites[0].results[1], "s", "FAIL - Too many IO operations performed");

  reset_test_io();
#if MILESTONE_9 == 1
  append_io_op(0, 3, DEVICE_IN, "one\n");
  append_io_op(0, 3, DEVICE_OUT, "one\n");

  append_io_op(0, 3, DEVICE_IN, "line\n");
  append_io_op(0, 3, DEVICE_OUT, "line\n");
  append_io_op(0, 3, DEVICE_IN, "\n");
#else
  append_io_op(0, 3, DEVICE_IN, "o");
  append_io_op(0, 3, DEVICE_OUT, "o");
  append_io_op(0, 3, DEVICE_IN, "n");
  append_io_op(0, 3, DEVICE_OUT, "n");
  append_io_op(0, 3, DEVICE_IN, "e");
  append_io_op(0, 3, DEVICE_OUT, "e");
  append_io_op(0, 3, DEVICE_IN, "\n");
  append_io_op(0, 3, DEVICE_OUT, "\none\n");

  append_io_op(0, 3, DEVICE_IN, "l");
  append_io_op(0, 3, DEVICE_OUT, "l");
  append_io_op(0, 3, DEVICE_IN, "i");
  append_io_op(0, 3, DEVICE_OUT, "i");
  append_io_op(0, 3, DEVICE_IN, "n");
  append_io_op(0, 3, DEVICE_OUT, "n");
  append_io_op(0, 3, DEVICE_IN, "e");
  append_io_op(0, 3, DEVICE_OUT, "e");
  append_io_op(0, 3, DEVICE_IN, "\n");
  append_io_op(0, 3, DEVICE_OUT, "\nline\n");
  append_io_op(0, 3, DEVICE_IN, "\n");
  append_io_op(0, 3, DEVICE_OUT, "\n");
#endif

  result = echo("echo");
  assert(result == 7, &suites[0].results[2], "sd", "FAIL Expected return 7, got ", result);
#if MILESTONE_9 == 1
  assert(io_idx > 4, &suites[0].results[3], "s", "FAIL - Missing IO operations on console");
  assert(io_idx < 6, &suites[0].results[3], "s", "FAIL - Too many IO operations performed");
#else
  assert(io_idx > 19, &suites[0].results[3], "s", "FAIL - Missing IO operations on conosle");
  assert(io_idx < 21, &suites[0].results[3], "s", "FAIL - Too many IO operations perofrmed");
#endif
  assert(1, &suites[0].results[4], "s", "");
}

static void hello_tests(void) {
  u8 hello(const char*);
  u8 result;
  register_device_action(DEVICE_OUT, spoof_write, NULL);

  reset_test_io();
  append_io_op(1, 3, DEVICE_OUT, "Hello, example!\n");
  result = hello("hello example");
  assert(result == 0, &suites[1].results[2], "sd", "FAIL - Expected return 0, got ", result);
  assert(io_idx > 0, &suites[1].results[3], "s", "FAIL - Write to console operation was not performed");
  assert(io_idx < 2, &suites[1].results[3], "s", "FAIL - Extra IO operations performed");

  reset_test_io();
  append_io_op(1, 1, DEVICE_OUT, "Error - bad argument\n");
  result = hello("hello");
  assert(result == 1, &suites[1].results[0], "sd", "FAIL - Expected return 1, got ", result);
  assert(io_idx > 0, &suites[1].results[1], "s", "FAIL - Write to console operation was not performed");
  assert(io_idx < 2, &suites[1].results[1], "s", "FAIL - Extra IO operations performed");
}

void shell_tests(void) {
  u8 shell(const char*);

  register_device_action(DEVICE_IN, spoof_read, NULL);
  register_device_action(DEVICE_OUT, spoof_write, NULL);
  IF_MS(3, shadows[HELLO].precall = spoof_hello);
  IF_MS(3, shadows[ECHO].precall = spoof_echo);

  reset_test_io();
#if MILESTONE_9 == 1
  append_io_op(2, 0, DEVICE_OUT, "shell$ ");
  append_io_op(2, 0, DEVICE_IN, "\n");
  append_io_op(2, 1, DEVICE_OUT, "shell$ ");
  append_io_op(2, 2, DEVICE_IN, "b\n");
  append_io_op(2, 2, DEVICE_OUT, "Unknown command\nshell$ ");
  append_io_op(2, 0, DEVICE_IN, "hello A\n");
#else
  append_io_op(2, 0, DEVICE_OUT, "shell$ ");
  append_io_op(2, 0, DEVICE_IN, "\n");
  append_io_op(2, 1, DEVICE_OUT, "\nshell$ ");
  append_io_op(2, 2, DEVICE_IN, "b");
  append_io_op(2, 2, DEVICE_OUT, "b");
  append_io_op(2, 2, DEVICE_IN, "\n");
  append_io_op(2, 2, DEVICE_OUT, "\nUnknown command\nshell$ ");

  append_io_op(2, 0, DEVICE_IN, "h");
  append_io_op(2, 0, DEVICE_OUT, "h");
  append_io_op(2, 0, DEVICE_IN, "e");
  append_io_op(2, 0, DEVICE_OUT, "e");
  append_io_op(2, 0, DEVICE_IN, "l");
  append_io_op(2, 0, DEVICE_OUT, "l");
  append_io_op(2, 0, DEVICE_IN, "l");
  append_io_op(2, 0, DEVICE_OUT, "l");
  append_io_op(2, 0, DEVICE_IN, "o");
  append_io_op(2, 0, DEVICE_OUT, "o");
  append_io_op(2, 0, DEVICE_IN, " ");
  append_io_op(2, 0, DEVICE_OUT, " ");
  append_io_op(2, 0, DEVICE_IN, "A");
  append_io_op(2, 0, DEVICE_OUT, "A");
  append_io_op(2, 0, DEVICE_IN, "\n");
  append_io_op(2, 0, DEVICE_OUT, "\n");
#endif
  shell(NULL);
}

static void user_space_tests(void) {
  hello_tests();
  echo_tests();
  shell_tests();
}


/* ---------------------------------------------------------- */

static i64 enter_userspace([[gnu::unused]] wrapper_alias_e name, ...) {
  void __real_user_start(void (*)(void));
  __real_user_start(user_space_tests);
  return 1;
}

static i64 intercept_console([[gnu::unused]] wrapper_alias_e name, ...) {
  device_t* uart = find_anonymous_device(0x10000000);
  if (!assert(uart != NULL, &suites[0].results[0], "s", "FAIL - UART device could not be found"))
    return 0;
#if MILESTONE_9 == 1
  device_t* tty = get_device_by_handle(uart->delegate);
  if (tty != NULL)
    tty->write = nostdout;
#endif
  uart->write = nostdout;
  return 0;
}

static void integration_tests([[gnu::unused]] override_t* shad) {
  shadows = shad;
  shadows[INIT_UART].postcall = intercept_console;
  shadows[USER_START].precall = enter_userspace;
}

#endif
