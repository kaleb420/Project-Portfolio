#include <blib.h>
#include <system.h>



/* --------------------------------------------------------------- *
 * 'hello' prints "Hello, <text>!\n" where <text> is the contents  *
 * following  "builtin_hello "  in  the  argument  and  returns 0. *
 * If  no  text exists,  print  an  error  and return  1  instead. *
 * --------------------------------------------------------------- */
u8 hello(const char* arg) {
	if (strcmp(arg, "hello")==0){
		printf("Error - bad argument\n");
		return 1;
	}
	char output[len(arg)+1];
	substring(arg, output, 6, len(arg));
	printf("Hello, %s!\n", output);
	return 0;
}
