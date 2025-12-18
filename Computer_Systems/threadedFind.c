#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <pthread.h>
#include <sys/stat.h>

typedef struct {
	char file_name[100];
	char input[100];
	long start;
	long end;
	long *results;
}threaded_data;

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

void *threaded_search(void *thread_struct){
	threaded_data *data=(threaded_data *)thread_struct;
	long occurences=0;
	long read=0;
	long offset=0;
	char buffer[512];
	char flow_over[100];
	char current_word[100];
        FILE *fptr = fopen(data->file_name, "r");
        while (offset<data->start){
		if (offset+512>=data->start){
			read=fread(buffer, sizeof(char), 1, fptr);
		}
		else
			read=fread(buffer, sizeof(char), 512, fptr);
		offset+=read;
	}
	long total=offset;
	read = fread(buffer, sizeof(char), 512, fptr);
        buffer[read] = '\0';
        while (read > 0 && total<data->end) { 
		total+=read;
		if (read >= strlen(data->input)) {
                        for (int j = 0; j < read - strlen(data->input); j++) {
                                if (buffer[j] == data->input[0]) {
                                        get_str(current_word, buffer, j, strlen(data->input));
                                        if (str_equal(current_word, data->input))
                                                occurences++;
                               }
                        }
                }
                long flow_len = store_flow_over(flow_over, buffer, read - strlen(data->input), read);
                read = fread(buffer, sizeof(char), 512, fptr);
                buffer[read] = '\0';
                if (read != 0) {
                        for (int k = 0; k < strlen(flow_over); k++) {
                                if (flow_over[k] == data->input[0]) {
                                        get_flow_over_str(current_word, flow_over, buffer, k, strlen(data->input), flow_len, read);
                                        if (str_equal(current_word, data->input) == 1)
                                                occurences++;
                                }
                        }
                }
        }
	long *occurences_ptr=malloc(sizeof(long));
	occurences_ptr=&occurences;
        fclose(fptr);
	return occurences_ptr;
}

void get_occurences(char *file_name, char *input, int threads) {
	struct stat file_info;
	long file_size=0;
	if (stat(file_name, &file_info)==0)
		file_size=file_info.st_size;
	pthread_t thread[threads];
	threaded_data data[threads];
	long result[threads];
	long segment=file_size/threads;
	for (int i=0; i<threads; i++){
		strcpy(data[i].file_name, file_name);
		strcpy(data[i].input, input);
		data[i].start=i*segment;
		if (i==threads-1)
			data[i].end=file_size;
		else 
			data[i].end=(i*segment)+segment;
		data[i].results=result;
		pthread_create(&thread[i], NULL, threaded_search, &data[i]);
	} 
	for (int i=0; i<threads; i++){
		long *occurences;
		pthread_join(thread[i], (void**)&occurences);
		result[i]=*occurences;
	}
	int total=0;
	for (int i=0; i<threads; i++){
		total+=result[i];
	}
	printf("%s - %d\n", file_name, total);
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
			get_occurences(argv[i], input, strtol(argv[1], NULL, 10));
			return 0;
		}
	}
}
