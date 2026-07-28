#ifndef H_BRISCLIB
#define H_BRISCLIB

/*
 *  This file contains all of the common variable and function definitions
 *  any given file is likely to need.
 */

#define NULL 0x0

typedef __INT8_TYPE__    i8;            /*                     */
typedef __INT16_TYPE__   i16;           /*                     */
typedef __INT32_TYPE__   i32;           /*                     */
typedef __INT64_TYPE__   i64;           /*  Fixed width types  */
typedef __UINT8_TYPE__   u8;            /*                     */
typedef __UINT16_TYPE__  u16;           /*                     */
typedef __UINT32_TYPE__  u32;           /*                     */
typedef __UINT64_TYPE__  u64;           /*                     */

typedef __builtin_va_list    va_list;

#define va_copy(dst, src)  __builtin_va_copy(dst, src)
#define va_start(va, ...) __builtin_va_start(va __VA_OPT__(,) __VA_ARGS__)
#define va_arg(va, type)   __builtin_va_arg(va, type)
#define va_end(va)         __builtin_va_end(va)

extern u8 ld_text_start;                /*                                               */
extern u8 ld_text_end;                  /*  These variables are automatically generated  */
extern u8 ld_data_start;                /*  during   compilation  and  placed  at   the  */
extern u8 ld_mem_start;                 /*  boundries between their respective segments  */
extern u8 ld_mem_end;                   /*                                               */

void* memset(void*, u8, u64);
void* memcpy(void*, const void*, u64);
i32   memcmp(const u8*, const u8*, u64);

u32 kprintf(const char *format, ...);
u32 printf(const char *format, ...);

u32 len(const char* str);

int strcmp(const char *str, char *str2);

void substring(const char *input, char *output, u64 start, u64 end);

void reverse(char *str, char *output);

u8 hello(const char *arg);

u8 echo(const char *arg);

u8 shell([[gnu::unused]] const char* arg);

void strcpy(const char *input, char *output);

#define ___START___ [[gnu::section(".text.user.entry")]]   /*  A hack that allows us to find the first user function  */

#define UART "uart"   /*  Device name used for Milestone-1 */
#define TTY  "tty"    /*  Device name used for Milestone-9 */


#endif
