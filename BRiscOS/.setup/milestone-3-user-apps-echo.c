#include <blib.h>
#include <system.h>

/* --------------------------------------------------------------- *
 * 'echo' reads in a line of text from the UART and prints it.  It *
 * will  comtinue to do  so until the line  read from the  UART is *
 * empty (indicated  by a \n\n sequence) and  return the number of *
 * characters printed in this way.                                 *
 *                                                                 *
 * Alternatively, a  string passed  to echo through  the arg param *
 * should  be printed  to the  console, then  echo  should  return *
 * immediately and return 0.                                       *
 * --------------------------------------------------------------- */
u8 echo(char* arg) {
  
  return 0;
}
