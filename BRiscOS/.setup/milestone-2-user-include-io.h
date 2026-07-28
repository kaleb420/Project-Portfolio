#ifndef H_IO
#define H_IO

#include <blib.h>

#define CONSOLE UART      /*  Defines which device the user mode output should use */

u32 printf(const char* format, ...);

#endif
