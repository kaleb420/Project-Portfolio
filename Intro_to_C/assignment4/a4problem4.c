#include <stdio.h>
#include <stdlib.h>
char **getXMLTagAndContent(char *s){
	int columns=0;
	int row=0;
	int length=1;
	int counter=0;
	while (s[length]!='\0'){ // count the length of the string
		length++;
	}
	char **str=(char **)malloc(3*sizeof(char *)); 
	for (int i=0; i<3; i++){
		str[i]=malloc(length*sizeof(char));
	}
	while (counter<length && columns<3){ // columns checked to account for edge cases that could result in a seg fault
		if (s[counter]=='>'){ // this is the last character of the first and third string, so it acts as a delimeter
			str[columns][row]=s[counter]; // set the row and column equal to >
			str[columns][row+1]='\0'; // add the null character
			columns+=1; // have the next column be filled in 
			row=-1; // set to -1 to provide correct calculations 
		}
		else if (counter>1 && s[counter]=='<'){ // same as last if statement but for < as the delimeter, and only have it split the third string, not the first one 
			str[columns][row]='\0';
			columns+=1;
			row=0;
			str[columns][row]=s[counter];
		}
		else
			str[columns][row]=s[counter]; // otherwise have the column and row be equal to the character at the counter index
		row++;
		counter++;
	}
	return str; 
}
