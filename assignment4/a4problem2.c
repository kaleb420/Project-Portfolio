#include <stdio.h>
#include <stdlib.h>
char **tokenize(char *s, char d, int *n){
	int columns=0;
	int rows=0;
	*n=1; // assign n a value in the function to aovid issues when printing in the main function
	char **token=(char **)malloc(sizeof(char *)*20); // dynamically allocate memory
	for (int i=0; i<20; i++){
		token[i]=malloc(sizeof(char)*20);
	}
	int j=0;
	while (s[j]!='\0'){ // run until null character is reached
		if (s[j]==d && s[j+1]!=d){ // if the current character is the delimiter and the next one isn't 
			if (j!=0 && s[j+1]!='\0'){ // and the character isn't the first or last in the list 
				(*n)+=1; // increment the delimeter counter
				columns+=1; // go to the next column
				rows=0; // reset the row count
			}
			j+=1; 
		}
		else if (s[j]==d && s[j+1]==d){ // do not count consecutive delimeter, instead just skip them
			j+=1;
		}
		else{
			token[columns][rows]=s[j]; // if it's not a delimeter assign the column and row to s[j]
			rows+=1; 
			j+=1;
		}
	}
	return token;
}
