#ifndef H_INTERRUPTS
#define H_INTERRUPTS

#include <blib.h>

#define INTR_S_TIMER  0x020     /*  Bitmask matching the enable timer interrupt bit     */
#define INTR_S_EXTERN 0x200     /*  Bitmask matching the enable external interrupt bit  */

u32 enable_interrupts(void);
u32 set_interrupt(u32);
u32 acknowledge_interrupt(u32);
void set_interrupt_handler(void (*)(void));
void init_interrupts(void);

#define syscall_t [[gnu::error("System calls may not be invoked directly")]] i64
#define trap_t    [[gnu::error("Trap handlers may not be called directly")]] i64

#endif
