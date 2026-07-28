#include <blib.h>
#include <system.h>
#include <io.h>

#define PROMPT "shell$ "

/* --------------------------------------------------- *
 * 'shell' runs an infinite loop  that prints a prompt *
 * to the user, reads in a command, parses the command *
 * and calls the corresponding function.               *
 * --------------------------------------------------- */
void inttostr(int x, char *output){
	if (x==0){
		output[0]='0';
		output[1]='\0';
		return;
	}
	char temp[1024];
	int i=0;
	while (x>=1){
		int temp_val=x%10;
		x/=10;
		temp[i++]=(char) temp_val+'0';
	}
	temp[i]='\0';
	reverse(temp, output);
}

/*void strcpy(const char *input, char *output){
	u32 i=0;
	while (i<len(input)){
		output[i]=input[i];
		i++;
	}
	output[i]='\0';
} */

void str_shift(char *input, char *output, int start, int shift){
        int length= (int) len(input);
	int i=0;
	while (i<length){
                if (i<start)
                        output[i]=input[i];
                else if (i+shift>=length)
                        break;
                else if (i==start && shift<0)
                                output[i]=' ';
                else
                        output[i]=input[i+shift];
		i++;
        }
	if (shift<0){
		output[i]=input[i+shift];
		i++;
	}
        output[i]='\0';
}

u8 shell([[gnu::unused]] const char* arg) {
	i64 dev=syscall(0, "uart", 4);
	u8 last_return=0;
	while (1){
		char buffer[1024];
		u8 ret_value=0;
		printf("shell$ ");
		int i=0;
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
		buffer[i-1]='\0';
		char output[1024];
		output[0]='\0';
		u32 length=len(buffer);
		for (u32 j=0; j<length; j++){
			if (buffer[j]=='$' && buffer[j+1]=='?'){
				inttostr(last_return, output);
				if (len(output)==1){
					buffer[j]=output[0];
					buffer[j+1]=' ';
					str_shift(buffer, output, (int) j+1, 1);
				}
				else if (len(output)==2){
					buffer[j]=output[0];
					buffer[j+1]=output[1];
				}
				else if (len(output)==3){
					buffer[j]=output[0];
					buffer[j+1]=output[1];
					buffer[j+2]=output[2];
					str_shift(buffer, output, (int) j+3, -1);
				}
			}
		}
		if (len(output)!=0 && len(output)!=2)
			strcpy(output, buffer);
		if (buffer[0]=='h' && buffer[1]=='e' && buffer[2]=='l' && buffer[3]=='l' && buffer[4]=='o'){
			i64 tid=syscall(5, hello, 0, buffer, len(buffer));
			syscall(7, tid);
			ret_value= (u8) syscall(8, tid);
		}
		else if (buffer[0]=='e' && buffer[1]=='c' && buffer[2]=='h' && buffer[3]=='o'){
			i64 tid=syscall(5, echo, 0, buffer, len(buffer));
			syscall(7, tid);
			ret_value= (u8) syscall(8, tid);
		}
		else if (buffer[0]=='\0')
			continue;
		else
			kprintf("Unknown command\n");
		last_return=ret_value;
	}
	return 0;
}
