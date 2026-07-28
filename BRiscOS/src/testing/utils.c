#include <blib.h>
#include <config.h>
#include <testing.h>
#include <wrapper_defs.h>
#include <printing.h>

extern override_t test_shadows[NUM_WRAPPERS];

static milestone_test_t unit_tests[MILESTONE+1];

static u32 current_ms = TEST_MILESTONE;
static u32 current_unit = 0;

extern u8 ld_bss_end;

#ifdef GENERATE_SUITES
typedef struct {
  const char* text;
  u32 count;
} counter_t;
static counter_t counters[MAX_UNIT_TESTS * MAX_SUITE_TESTS] = {0};
#endif

static char scratch[1024];

i32 assert(i32 pred, result_t* result, const char* template, ...) {
  char err[FORMATTED_RES_LEN];
  va_list args;

  va_start(args, template);
  vstring_builder(err, FORMATTED_RES_LEN, template, &args);
  if (result->lock == false) {
    if (pred == 0) {
      string_builder(result->text, MAX_RESULT_LEN, "sss", ERROR, err, RESET);
      result->lock = true;
    [[gnu::unused]] error:
    }
    else
      string_builder(result->text, MAX_RESULT_LEN, "sss", SUCCESS, "OK", RESET);
  }
  va_end(args);
  return pred;
}

i64 nostdout([[gnu::unused]] device_t* dev, [[gnu::unused]] u64 handle, [[gnu::unused]] const char* buffer, u64 len) {
  return (i64)len;
}

static void capture_snapshot(void) {
  u64 capture_size = (u64)((u64*)(u64)&ld_bss_end - (u64*)(u64)&ld_data_start);
  u64* snapshot_base = (u64*)(u64)(&ld_mem_end - (NUM_THREADS * (((&ld_mem_end - &ld_mem_start) / 2) / NUM_THREADS)));
  u64* source_base = (u64*)(u64)&ld_data_start;
  snapshot_base -= capture_size;

  for (u64 i=0; i<capture_size; i++)
    snapshot_base[i] = source_base[i];
}

static void restore_snapshot(void) {
  u64 capture_size = (u64)((u64*)(u64)&ld_bss_end - (u64*)(u64)&ld_data_start);
  u64* snapshot_base = (u64*)(u64)(&ld_mem_end - (NUM_THREADS * (((&ld_mem_end - &ld_mem_start) / 2) / NUM_THREADS)));
  u64* source_base = (u64*)(u64)&ld_data_start;
  u64 stash = current_ms;
  snapshot_base -= capture_size;

  snapshot_base[-1] = (stash << 32) | (current_unit & 0xFFFFFFFF);
  for (u64 i=0; i<capture_size; i++)
    source_base[i] = snapshot_base[i];
  current_ms = (u32)(snapshot_base[-1] >> 32);
  current_unit = snapshot_base[-1] & 0xFFFFFFFF;
}

static void test_puts(const char* str) {
  #define UART_ADDR ((u8*)0x10000000)
  for (i32 i=0; str[i] != '\0'; i++) {
    while (!(UART_ADDR[5] & 0x20));
    UART_ADDR[0] = str[i] & 0xff;
  }
}

static void get_len_stats(test_suite_t* suite, i32* max_prompt, i32* max_result) {
  *max_result = 0;
  *max_prompt = 0;
  for (u32 j=0; suite->prompts[j] != NULL; j++) {
    i32 prompt_len = test_strlen(suite->prompts[j]);
    i32 result_len = test_strlen(suite->results[j].text);
    if (prompt_len > *max_prompt)
      *max_prompt = prompt_len;
    if (result_len > *max_result)
      *max_result = result_len;
  }
}

static void set_error(const char* error, const char* stat, unit_test_t* test) {
  for (u32 i=0; i<test->num_suites; i++)
    for (i32 j=0; test->suites[i].prompts[j] != NULL; j++)
      if (test->suites[i].results[j].text[0] == '\0')
	string_builder(test->suites[i].results[j].text, MAX_RESULT_LEN, "sss", stat, error, RESET);
}

static void print_unit(const char* on_error, const char* stat, unit_test_t* test) {
  i32 max_prompt, max_result;
  
  set_error(on_error, stat, test);
#ifdef GENERATE_SUITES
#ifndef SUITE_FILES
#define SUITE_FILES ""
#endif
  u32 countlen = 0;
  
  test_puts(string_builder(scratch, 1024, "sssds",
			   "name: ", test->unit, "\n"
			   "cmdname: Setup\n"
			   "cmd: tar -xzvf source.tar.gz; "
			   "mkdir -p src/testing/include; "
			   "mkdir -p src/testing/units; "
			   "mv *.c src/testing; "
			   "mv *.h src/testing/include; "
			   "mv *.ut src/testing/units; "
			   "scons build --mode=metal milestone=", current_ms, "\n"
			   "student: [source.tar.gz]\n"));
  test_puts(string_builder(scratch, 1024, "sss",
			   "instructor: [", SUITE_FILES, "]\n"));
  test_puts(string_builder(scratch, 1024, "sds",
			   "image: \"Ubuntu [riscv] [scons]\"\n"
			   "tests:\n"
			   "  - name: Run Tests\n"
			   "    visible: false\n"
			   "    commands:\n"
			   "      - name: __Run\n"
			   "        cmd: scons test milestone=", current_ms, " --logfile=.log\n"
			   "        visible: false\n"
			   "      - name: __Log\n"
			   "        cmd: cat .log\n"
			   "        visible: false\n"));
#else
  test_puts(string_builder(scratch, 1024, "sssss", "│\n│    ",  EMPH, test->unit, RESET, "\n"));
#endif
  for (u32 i=0; i<test->num_suites; i++) {
    i32 nlen = test_strlen(test->suites[i].section);
    i32 hlen = nlen / 2;

    get_len_stats(&test->suites[i], &max_prompt, &max_result);
    if (max_result < nlen - hlen - 1) max_result = nlen - hlen - 1;
#ifdef GENERATE_SUITES
    test_puts(string_builder(scratch, 1024, "sss",
			     "  - name: ", test->suites[i].section, "\n"
			     "    commands:\n"));
    
#else
    if (i == 0)
      test_puts(string_builder(scratch, 1024, "sss", "├", FADED, "─┬"));
    else
      test_puts(string_builder(scratch, 1024, "sss", "│", FADED, " ├"));
    test_puts(FADED);
    for (i32 v=0; v<2 + max_prompt - (nlen - hlen); v++)
      test_puts((i == 0 ? "─" : "┈"));
    test_puts(string_builder(scratch, 1024, "ssssss", " ", RESET, TITLE, test->suites[i].section, RESET, " "));

    test_puts(FADED);
    for (i32 v=0; v<1 + max_result - hlen; v++)
      test_puts((i == 0 ? "─" : "┈"));
    test_puts("╮\n");
    test_puts(RESET);
#endif

    for (u32 j=0; test->suites[i].prompts[j] != NULL; j++) {
#ifdef GENERATE_SUITES
      u32 rep_id;
      for (rep_id=0; rep_id < countlen; rep_id++)
	if (test_strcmp(counters[rep_id].text, test->suites[i].prompts[j]) == 0)
	  break;
      counters[rep_id].count += 1;
      counters[rep_id].text = test->suites[i].prompts[j];
      if (rep_id == countlen)
	countlen += 1;
      test_puts(string_builder(scratch, 1024, "sssssds",
			       "      - name: ", test->suites[i].prompts[j], "\n"
			       "        cmd: grep \"", test->suites[i].prompts[j], "\" .log | head -n ", counters[rep_id].count, " | tail -n 1\n"
			       "        stdout: "));
#endif
      i32 pad = max_prompt - test_strlen(test->suites[i].prompts[j]);
      test_puts(string_builder(scratch, 1024, "ssssss", RESET, "│", FADED, " │ ", RESET, test->suites[i].prompts[j]));
      for (i32 v=0; v<pad; v++) test_puts(" ");
      test_puts(string_builder(scratch, 1024, "sss", FADED, " ╎ ", RESET));
#ifdef GENERATE_SUITES
      test_puts("OK");
#else
      test_puts(string_builder(scratch, 1024, "ss", test->suites[i].results[j].text, FADED));
#endif
      for (i32 v=0; v<max_result-test_strlen(test->suites[i].results[j].text); v++)
	test_puts(" ");
      test_puts(string_builder(scratch, 1024, "ss", " │\n", RESET));
#ifndef GENERATE_SUITES
      test_puts(FADED);
      if (test->suites[i].prompts[j+1] != NULL) {
	test_puts(string_builder(scratch, 1024, "ssss", RESET, "│", FADED, " ├"));
	for (i32 v=0; v<max_prompt+2; v++)
	  test_puts("╌");
	test_puts("┼");
	for (i32 v=0; v<max_result+2; v++)
	  test_puts("╌");
	test_puts("┤\n");
      }
      else {
	test_puts(string_builder(scratch, 1024, "ssss", RESET, "│", FADED, (i < test->num_suites - 1 ? " ├" : " ╰")));
	for (i32 v=0; v<max_prompt+max_result+5; v++)
	  test_puts("─");
	test_puts("╯\n");
      }
      test_puts(RESET);
#endif
    }
  }
}

device_t* find_anonymous_device(u64 addr) {
  device_t* dev;
  for (u32 i=0; i<NUM_DEVS; i++) {
    if ((dev = get_device_by_handle(i))->addr == (u8*)addr)
      return dev;
  }
  return NULL;
}

i32 override_device(const char* ns, i64 (*read)(device_t*, u64, char*, u64), i64 (*write)(device_t*, u64, const char*, u64)) {
  device_t* dev = get_device_by_name(ns);
  if (dev == NULL)
    return -1;
  dev->write = write;
  dev->read = read;
  return 0;
}

void print_result(const char* on_error, const char* stat) {
  if (unit_tests[current_ms].units[current_unit].fn != NULL) {
    print_unit(on_error, stat, &unit_tests[current_ms].units[current_unit]);
  }
  else if (unit_tests[current_ms].integration.fn != NULL && current_unit <= unit_tests[current_ms].num_units) {
    print_unit(on_error, stat, &unit_tests[current_ms].integration);
  }

  if (unit_tests[current_ms].num_units <= current_unit) {
#ifndef GENERATE_SUITES
    test_puts("┕━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┅┅\n");
#endif
    current_unit = 0;
    current_ms += 1;
  }
  else {
    current_unit += 1;
  }
}

#define SYSCON_ADDR 0x100000
static void reset(bool shutdown) {
  if (shutdown) {
    *(u32*)SYSCON_ADDR = 0x5555;
  }
  (*(u8*)0x0) = 'r';           /* Purposely trigger an exception to force a kernel panic reset */
}

extern u8 ld_init_array_start;
extern u8 ld_init_array_end;
static void initialize_all_tests(void) {
  void (**funcs)(void) = (void (**)(void))(u64)&ld_init_array_start;
  i64 num_funcs = (&ld_init_array_end - &ld_init_array_start) / (i64)sizeof(void (*)(void));
  memset(unit_tests, 0, sizeof(milestone_test_t) * MILESTONE+1);
  for (i32 i=0; i<num_funcs; i++)
    if (funcs[i] != NULL)
      funcs[i]();
}

static i64 silent_stdout([[gnu::unused]] device_t* dev, [[gnu::unused]] u64 handle, [[gnu::unused]] const char* buffer, u64 len) {
  return (i64)len;
}

static i64 intercept_console([[gnu::unused]] wrapper_alias_e name, ...) {
  device_t* uart = find_anonymous_device(0x10000000);
  if (uart == NULL)
    return 0;

  uart->write = silent_stdout;
  return 0;
}

[[gnu::unused]]
static i64 intercept_tty([[gnu::unused]] wrapper_alias_e name, ...) {
  device_t* tty, *uart = find_anonymous_device(0x10000000);
  if (uart == NULL || (tty = get_device_by_handle(uart->delegate)) == NULL)
    return 0;

  tty->write = silent_stdout;
  return 0;
}

void run_unit_tests(i32* timeout) {
  if (current_ms == 0) {
    current_ms += 1;
  }
  if ((current_ms == 1 || (TEST_MILESTONE != 1 && current_ms == TEST_MILESTONE)) && current_unit == 0) {
    initialize_all_tests();
    capture_snapshot();
  }
  if (current_ms > MILESTONE || (TEST_MILESTONE != 0 && current_ms != TEST_MILESTONE))
    reset(true);

  restore_snapshot();
  *timeout = TIMEOUT_TICKS;
  if (current_unit == 0) {
#ifndef GENERATE_SUITES
    test_puts(string_builder(scratch, 1024, "sds", "\n┍━━━━━━━━ Unit Tests for Milestone ", current_ms, " ┅┅\n"));
#endif
  }

  memset(test_shadows, 0, sizeof(override_t) * NUM_WRAPPERS);
  test_shadows[INIT_UART].postcall = intercept_console;
  IF_MS(9, test_shadows[INIT_TTY].postcall = intercept_tty);
  if (unit_tests[current_ms].units[current_unit].fn != NULL) {
    unit_tests[current_ms].units[current_unit].fn(test_shadows);
  }
  else if (unit_tests[current_ms].integration.fn != NULL) {
    unit_tests[current_ms].integration.fn(test_shadows);
    return;
  }
  
  reset(false);
}

void register_test(const char* unit, u32 milestone, void (*fn)(override_t*), test_suite_t* suites, u32 num_suites) {
  if (milestone > MILESTONE)
    return;

  milestone_test_t* tests = &unit_tests[milestone];
  unit_test_t* entry = &tests->integration;
  if (memcmp((u8*)unit, (u8*)"INTEGRATION", 11) != 0) {
    if (tests->num_units >= MAX_UNIT_TESTS)
      return;
    entry = &tests->units[tests->num_units++];
  }
  entry->unit = unit;
  entry->fn = fn;
  entry->suites = suites;
  entry->num_suites = num_suites;
}
