#ifndef H_SLEEP
#define H_SLEEP

#include <blib.h>
#include <queues.h>

extern queue_head_t sleep_list;

void init_timer(void);
void handle_timer(void);

#endif
