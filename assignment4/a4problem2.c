#include <stdio.h>
#include <stdlib.h>
char **tokenize(char *s, char d, int *n){
	int columns=0;
	int rows=0;
	char **token=(char **)malloc(sizeof(char *)*20);
	for (int i=0; i<20; i++){
		token[i]=malloc(sizeof(char)*20);
	}
	int j=0;
	while (s[j]!='\0'){
		if (s[j]==d){
			n+=1;
			columns+=1;
			rows=0;
		}
		token[columns][rows]=s[j];
		rows+=1;
		j+=1;
	}
	free(token);
	return token;
}
