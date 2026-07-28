#include <blib.h>
#include <system.h>
#include <io.h>

/*  printf - This function takes a format string and optional arguments from   *
 *           the caller and prints the text to the default output device after *
 *           processing any additional arguments to convert them into strings. *
 *           The function ends on encountering a '\0' character in the format  *
 *           string.                                                           *
 *  @format - char*  A buffer containing the printable text, as well as any    *
 *                   format characters corresponding with optional arguments   *
 *  @...    - Any    Optional arguments which may be of any type, processed    *
 *                   using the `variadic arguments` gcc feature.               *
 *  @return - u32    Number of characters printed.                             */
u32 printf(const char* format, ...) {
  va_list ap;
  va_start(ap, format);



  va_end(ap);
  return 0;
}
