#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int str_equal(char *str1, char *str2){
	if (strlen(str1)!=strlen(str2))
		return 0;
	for (int i=0; i<strlen(str1); i++){
		if (str1[i]!=str2[i])
			return 0;
	}
	return 1;
}

void get_flow_over_str(char *current_word, char *flow_over, char *buffer, int start_index, int len, int flow_len, int read){
	int index=0;
	for (int i=start_index; i<flow_len && index<len; i++){
		current_word[index++]=flow_over[i];
	}
	int j=0;
	while (index<len && j<read){
		current_word[index++]=buffer[j++];
	}
	current_word[index]='\0';
}

void get_str(char *current_word, char *buffer, int start_index, int len){
	int i=0;
	while (i<len){
		current_word[i++]=buffer[start_index++];
	}
	current_word[i]='\0';
}

int store_flow_over(char *flow_over, char *buffer, int start_index, int read){
	int i=0;
	while (start_index<read){
		flow_over[i++]=buffer[start_index++];
	}
	flow_over[i]='\0';
	return i;
}

int main(int argc, char *argv[]){
	char input[100];
	printf("Enter a word: ");
        scanf("%s", input);
	for (int i=1; i<argc; i++){
		pid_t pid=fork();
		if (pid>0){
			wait(NULL);
		}
		else if (pid==0){
			char buffer[512];
                	char current_word[100];
                	char flow_over[100];
        	        long occurences=0;
	                int read=0;
			FILE* fptr=fopen(argv[i], "r");
			read=fread(buffer, sizeof(char), 512, fptr);
			buffer[read]='\0';
			while (read>0){
				if (read>=strlen(input)) {
					for (int j=0; j<read-strlen(input); j++){
						if (buffer[j]==input[0]){
							get_str(current_word, buffer, j, strlen(input));
							if (str_equal(current_word, input))
								occurences++;
						}
					}
				}
				int flow_len=store_flow_over(flow_over, buffer, read-strlen(input), read); 
				read=fread(buffer, sizeof(char), 512, fptr);
				buffer[read]='\0';
				if (read!=0){
					for (int k=0; k<strlen(flow_over); k++){
						if (flow_over[k]==input[0]){
							get_flow_over_str(current_word, flow_over, buffer, k, strlen(input), flow_len, read);
							if (str_equal(current_word, input)==1)
								occurences++;
						}
					}
				}	
			}
			fclose(fptr);
			printf("%s - %ld\n", argv[i], occurences);
			return 0;
		}
	}
}
