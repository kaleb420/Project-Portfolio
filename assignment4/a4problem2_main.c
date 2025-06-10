#include <stdio.h>
#include <stdlib.h>
#include "a4problem2.h"
int main(void){
	char *s="hello world, how are, you doing?";
	char d=',';
	int *n=0;
	char **token=tokenize(s, d, n);
	for (int i=0; i<*n+1; i++){
		printf("%s", token[i]);
	}
	printf("n is populated with %d", *n);
}
