#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main(void){
	char input[4];
	int pipefd[2];
	pipe(pipefd);
	pid_t p=fork();
	if (p>0) {
		printf("prompt$ ");
		scanf("%s", input);
		write(pipefd[1], input, 4);
		close(pipefd[1]);
		wait(NULL);
	}
	else if (p==0) {
		read(pipefd[0], input, 4);
		if (input[0]=='i')
			execl("/usr/bin/id", "id", NULL);
		else if (input[0]=='p')
			execl("/usr/bin/pwd", "pwd", NULL);
		else if (input[0]=='d')
			execl("/usr/bin/date", "date", NULL);
		close(pipefd[0]);
	}
	printf("Done -  %s", input);
}
