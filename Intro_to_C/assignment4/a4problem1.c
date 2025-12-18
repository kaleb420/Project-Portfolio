#include <stdio.h>
int strLen(const char* str){
	int counter=0;
	if (str[counter]=='\0') // count number of characters until null character is reached
		return 0; 
	while (str[counter]!='\0'){
		counter++;
	}
	return counter;
}
void strCpy(char* destination, char* source){ // copy source to destination until null character is reached
	int counter=0;
	while (source[counter]!='\0'){
		destination[counter]=source[counter];
		counter++;
	}
}
void strNCpy(char* destination, const char* source, int n){ // same as last but use n as the upper bounds, and check if n is greater than the character length for edge cases
	for (int i=0; i<n; i++){
		if (source[i]=='\0')
			break;
		destination[i]=source[i];
	}
}
void strCat(char* destination, char* source){ 
	int destination_counter=0;
	while (destination[destination_counter]!='\0'){ // count how many characters are in the destination string
		destination_counter++;
	}
	int counter=0;
	while (source[counter]!='\0'){
		destination[destination_counter]=source[counter]; // count how many characters are in the source string, while copying each character onto the end of the destination string
		counter++;
		destination_counter++;
	}
	destination[destination_counter+1]='\0'; // add null terminator to the end of destination
}
const char * strChr(const char * str, int character){
	int counter=0;
	while (str[counter]!='\0'){
		if (str[counter]==character) // if the string index is the character inputted, return the character found through the index at a specific location
			return str[counter];
		counter++;
	}
}
