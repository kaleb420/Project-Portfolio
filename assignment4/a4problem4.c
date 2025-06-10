#include <stdio.h>
#include <stdlib.h>
char **getXMLTagAndContent(char *s){
	int columns=0;
	int row=0;
	int length=1;
	int counter=0;
	while (s[length]!='\0'){
		length++;
	}
	char **str=(char **)malloc(3*sizeof(char *));
	for (int i=0; i<3; i++){
		str[i]=malloc(length*sizeof(char));
	}
	while (counter<length && columns<3){
		if (s[counter]=='>'){
			str[columns][row]=s[counter];
			str[columns][row+1]='\0';
			columns+=1;
			row=-1;
		}
		else if (counter>1 && s[counter]=='<'){
			str[columns][row]='\0';
			columns+=1;
			row=0;
			str[columns][row]=s[counter];
		}
		else
			str[columns][row]=s[counter];	
		row++;
		counter++;
	}
	/* for (int j=0; j<3; j++){
		printf("%s\n", str[j]);
	} */
	return str; 
}
