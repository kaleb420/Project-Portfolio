#include <blib.h>
#include <system.h>
#include <syscall.h>
#include <io.h>

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
void substring(const char *input, char *output, u64 start, u64 end){
	if (len(input)>end)
		return;
	for (u64 i=start; i<end; i++){
		output[i-start]=input[i];
	}
	output[end-start]='\0';
}

int strcmp(const char *str, char *str2){
	int i=0;
	while (str[i]!='\0' && str2[i]!='\0'){
		if (str[i]<str2[i])
			return -1;
		else if (str[i]>str2[i])
			return 1;
		i++;
	}
	if (str[i]=='\0' && str2[i]=='\0')
		return 0;
	else if (str[i]=='\0')
		return -1;
	else
		return 1;
}

u8 echo(const char* arg) {
	char output[len(arg)+1];
	if (arg[4]!='\0'){
		substring(arg, output, 5, len(arg));
		printf("%s\n", output);
		return 0;
	}
	i64 dev=syscall(0, "uart", 4);
	u32 total=0;
	while (1){
		char buffer[1024];
		u32 i=0;
		while (1){
			char c;
			i64 r=syscall(2, dev, &c, 1);
			if (r==1){
				printf("%c", c);
				buffer[i++]=c;
				if (buffer[i-1]=='\n')
					break;
			}
		}
		total+=i-1;
		buffer[i]='\0';
		if (buffer[0]=='\n' && buffer[1]=='\0') {
			syscall(1, dev);
			return (u8) total;
		}
		printf("%s", buffer);
	}
}
