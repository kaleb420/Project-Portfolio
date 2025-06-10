#include <stdio.h>
int strLen(const char* str){
	int counter=0;
	if (str[counter]=='\0')
		return 0; 
	while (str[counter]!='\0'){
		counter++;
	}
	return counter;
}
void strCpy(char* destination, char* source){
	int counter=0;
	while (source[counter]!='\0'){
		destination[counter]=source[counter];
		counter++;
	}
}
void strNCpy(char* destination, const char* source, int n){
	for (int i=0; i<n; i++){
		if (source[i]=='\0')
			break;
		destination[i]=source[i];
	}
}
void strCat(char* destination, char* source){
	int counter=0;
	while (source[counter]!='\0'){
		destination[counter]=source[counter];
		counter++;
	}
	destination[counter]=source[0];
	destination[counter+1]='\0';
}
const char * strChr(const char * str, int character){
	int counter=0;
	while (str[counter]!='\0'){
		if (str[counter]==character)
			return str[counter];
		counter++;
	}
}
