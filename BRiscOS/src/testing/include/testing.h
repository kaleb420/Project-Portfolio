#ifndef TESTING_H
#define TESTING_H

#include <complete.h>
#include <macrotools.h>
#include <guard.h>
#include <wrapper_defs.h>

#define MAX_UNIT_TESTS 16
#define MAX_SUITE_TESTS 16
#define MAX_RESULT_LEN 256
#define FORMATTED_RES_LEN MAX_RESULT_LEN - 13

#define TIMEOUT_TICKS 15

#ifndef MILESTONE
#define MILESTONE 0
#endif

#ifndef TEST_MILESTONE
#define TEST_MILESTONE 1
#endif

#define FORCE_RESCHED 500

#ifndef USERSPACE
#include <devices.h>

device_t* find_anonymous_device(u64);
i32 override_device(const char*, i64 (*)(device_t*, u64, char*, u64), i64 (*)(device_t*, u64, const char*, u64));
i64 nostdout(device_t*, u64, const char*, u64);
#endif

typedef struct {
  u32 numcalls;
  i64 (*precall)(wrapper_alias_e, ...);
  i64 (*postcall)(wrapper_alias_e, ...);
} override_t;



typedef struct {
  char text[MAX_RESULT_LEN];
  bool lock;
} result_t;

typedef struct {
  const char* section;
  const char* prompts[MAX_SUITE_TESTS];
  result_t results[MAX_SUITE_TESTS];
} test_suite_t;

typedef struct {
  const char* unit;
  void (*fn)(override_t*);
  test_suite_t* suites;
  u32 num_suites;
} unit_test_t;

typedef struct {
  unit_test_t integration;
  unit_test_t units[MAX_UNIT_TESTS];
  u32 num_units;
} milestone_test_t;



void run_unit_tests(i32*);
void print_result(const char*, const char* stat);
void register_test(const char*, u32, void (*)(override_t*), test_suite_t*, u32);
char* string_builder(char* buf, i32 len, const char* fmt, ...);
char* vstring_builder(char* buf, i32 len, const char* fmt, va_list args);
i32 test_strcmp(const char*, const char*);
i32 test_strlen(const char*);
i32 assert(i32, result_t*, const char*, ...);

#define REGISTER(name, ms, fn, suites, num)                             \
  static test_suite_t suites[num];                                      \
  static void fn(override_t*);                                          \
  [[gnu::constructor]] static void CAT(register, CAT(_, name))(void) {	\
    register_test(#name, ms, fn, suites, num);	                        \
  } struct CAT(name, CAT(CAT(_,__COUNTER__), CAT(_,ms)))
#endif
