#ifndef ACTIONQUEUE_T
#define ACTIONQUEUE_T

#include <blib.h>
#include <wrapper_defs.h>

typedef struct {
  wrapper_alias_e actionid;
  void* context;
  u64 ctxsz;
} aq_entry_t;

typedef i64 (*actor)(wrapper_alias_e, ...);

actor register_action(wrapper_alias_e alias, void* (*delegate)(wrapper_alias_e, va_list, u64*), i64 (*validator)(u32, void*));
void register_device_action(wrapper_alias_e, void* (*)(wrapper_alias_e, va_list, u64*), i64 (*)(u32, void*));
i64 validate_actions(wrapper_alias_e* order, i64 n);

#endif
