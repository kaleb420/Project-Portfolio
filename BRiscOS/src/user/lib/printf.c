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
u32 len(const char* str){
        u32 i=0;
        while (*(str+i)!='\0'){
                i++;
        }
        return i;
}

void reverse(char *str, char *output){
        int length=(int) len(str);
        for (int i=0; i < length; i++){
                output[i]=str[length-1-i];
        }
        output[length]='\0';
}

u32 printf(const char* format, ...){
	i64 dev=syscall(0, "uart", 4);
        va_list ap;
        va_start(ap, format);

        u32 length=len(format);
        for (u32 i=0; *(format+i)!='\0'; i++){
                if (*(format+i)=='%') {
                        if (*(format+i+1)=='d') {
                                int va_int=va_arg(ap, int);
                                if (va_int<0) {
					syscall(3, dev, "-", 1);
                                        va_int=-va_int;
                                }
                                int j=0;
                                char temp[100];
				while (va_int!=0){
                                        int decimal=(va_int%10)+'0';
                                        temp[j++]=(char) decimal;
                                        va_int/=10;
                                }
                                temp[j]='\0';
                                if (j==0)
					syscall(3, dev, "0", 1);
                                else {
                                        char output[len(temp)+1];
                                        reverse(temp, output);
					syscall(3, dev, output, len(output));
                                }
                        }
                        else if (*(format+i+1)=='c') {
                                int va_int=va_arg(ap, int);
                                char va_char=(char) va_int;
                                char *location=&va_char;
				syscall(3, dev, location, 1);
                        }
                        else if (*(format+i+1)=='x') {
                                int va_int=va_arg(ap, int);
				syscall(3, dev, "0x", 2);
                                u32 va_hex=(u32) va_int;
                                u32 j=0;
                                char temp[100];
                                while (va_hex!=0){
                                        int hex=va_hex%16;
                                        if (hex<10)
                                                hex=hex+'0';
                                        else
                                                hex=(hex-10)+'a';
                                        temp[j++]=(char) hex;
                                        va_hex/=16;
                                }
                                temp[j]='\0';
                                if (j==0)
					syscall(3, dev, "0", 1);
				else {
                                        char output[len(temp)+1];
                                        reverse(temp, output);
					syscall(3, dev, output, len(output));
                                }
                        }
                        else if (*(format+i+1)=='%')
				syscall(3, dev, "%", 1);
			else if (*(format+i+1)=='s'){
				char *va_int=va_arg(ap, char *);
				while (*va_int!='\0'){
					syscall(3, dev, va_int, 1);
					va_int++;
				}
			}
                        i+=2;
                        if (*(format+i)=='\0')
                                break;
                }
                if (*(format+i)!='%')
			syscall(3, dev, format+i, 1);
                else
                        i--;
        }
        va_end(ap);
	syscall(1, dev);
	return length;
}
