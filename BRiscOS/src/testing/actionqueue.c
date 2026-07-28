#include <blib.h>
#include <testing.h>
#include <wrapper_defs.h>
#include <devices.h>
#include <actionqueue.h>

#define QUEUE_SZ 512

typedef struct {
  void* (*delegate)(wrapper_alias_e, va_list, u64*);
  i64 (*validator)(u32, void*);
  u32 idx;
} delegate_t;

static delegate_t delegates[NUM_WRAPPERS];
static aq_entry_t queue[QUEUE_SZ];
static u32 count = 0;


static i32 append_action(wrapper_alias_e actionid, void* ctx, u64 sz) {
  if (count >= QUEUE_SZ) return -1;
  memcpy(&queue[count++], &(aq_entry_t) { .actionid=actionid, .context=ctx, .ctxsz=sz }, sizeof(aq_entry_t));
  return 0;
}

static i64 delegate_wrapper(wrapper_alias_e name, ...) {
  void* context = NULL;
  u64 ctxlen = 0;
  va_list args;
  va_start(args, name);
  if (delegates[name].delegate != NULL)
    context = delegates[name].delegate(name, args, &ctxlen);
  append_action(name, context, ctxlen);
  va_end(args);
  return 1;
}

static i64 _va_wrapper(void* (*delegate)(wrapper_alias_e, va_list, u64*),  wrapper_alias_e name, ...) {
  u64 resultsize = 0;
  i64 result = -1;
  va_list args;
  va_start(args, name);
  if (delegate != NULL)
    result = (i64)delegate(name, args, &resultsize);
  append_action(name, (void*)result, 8);
  va_end(args);
  return result;
}

static i64 open_wrapper(device_t* dev, const char* path, u64 len) {
  return _va_wrapper(delegates[DEVICE_OPEN].delegate, DEVICE_OPEN, dev, path, len);
}

static i64 close_wrapper(device_t* dev, u64 desc) {
  return  _va_wrapper(delegates[DEVICE_CLOSE].delegate, DEVICE_CLOSE, dev, desc);
}

static i64 write_wrapper(device_t* dev, u64 handle, const char* buffer, u64 len) {
  return _va_wrapper(delegates[DEVICE_OUT].delegate, DEVICE_OUT, dev, handle, buffer, len);
}

static i64 read_wrapper(device_t* dev, u64 handle, char* buffer, u64 len) {
  return _va_wrapper(delegates[DEVICE_IN].delegate, DEVICE_IN, dev, handle, buffer, len);
}



actor register_action(wrapper_alias_e alias, void* (*delegate)(wrapper_alias_e, va_list, u64*), i64 (*validator)(u32, void*)) {
  memcpy(&delegates[alias], &(delegate_t) { .delegate=delegate, .validator=validator, .idx=0 }, sizeof(delegate_t));
  return delegate_wrapper;
}

void register_device_action(wrapper_alias_e alias, void* (*delegator)(wrapper_alias_e, va_list, u64*), i64 (*validator)(u32, void*)) {
  device_t* dev = find_anonymous_device(0x10000000);
  if (dev == NULL)
    return;
#if MILESTONE_9 == 1
  dev = get_device_by_handle(dev->delegate);
  if (dev == NULL)
    return;
#endif
  memcpy(&delegates[alias], &(delegate_t) { .delegate=delegator, .validator=validator, .idx=0 }, sizeof(delegate_t));
  switch (alias) {
  case DEVICE_OPEN:
    dev->open = open_wrapper; break;
  case DEVICE_CLOSE:
    dev->close = close_wrapper; break;
  case DEVICE_IN:
    dev->read = read_wrapper; break;
  case DEVICE_OUT:
    dev->write = write_wrapper; break;
  default:
    break;
  }
}

/*
 *  Returns the number of actions that differ.  If fewer actions were taken than expected
 *  Returns NULL in `actual` to indicate which actions were not taken.
 *  Otherwise, `actual` indicates the index of the first expected action that was incorrect.
 */
i64 validate_actions(wrapper_alias_e* order, i64 n) {
  u32 i;
  if (order == NULL) {
    for (i=0; i<count; i++)
      if (delegates[queue[i].actionid].validator)
	delegates[queue[i].actionid].validator(delegates[queue[i].actionid].idx++, &queue[i].context);
    goto cleanup_validate_actions;
  }
  
  for (i=0; i<count && i<n; i++) {
    if ((queue[i].actionid != order[i]) ||
	(delegates[order[i]].validator && delegates[order[i]].validator(delegates[order[i]].idx++, &queue[i].context) != 0))
      break;
  }

 cleanup_validate_actions:
  i = count;
  count = 0;
  for (i32 j=0; j<NUM_WRAPPERS; j++)
    delegates[j].idx = 0;
  return i - n;
}
