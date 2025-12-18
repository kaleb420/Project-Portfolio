#include <stdio.h>
#include <stdlib.h>
#include "a4problem2.h"
int main(void){
	char *s="! !!! empty space!.!.!?!";
	char d='!';
	int n;
	char **token=tokenize(s, d, &n);
	for (int i=0; i<n; i++){
		printf("%s", token[i]);
	}
	printf("\n%d\n", n);
	free(token);
}
