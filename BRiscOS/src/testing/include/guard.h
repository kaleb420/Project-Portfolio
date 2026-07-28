#ifndef GUARD_H
#define GUARD_H

typedef struct {
  u64 pc;
  u64 reg[31];
  u8 registered;
} guard_t;

extern guard_t guard_point;

void restore_guard(i64);
i64 set_guard(void);
void clear_guard(void);


#define TRY if (set_guard() == 0)
#define CATCH(f) else f; clear_guard();

#endif
