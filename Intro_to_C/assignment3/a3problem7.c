#include <stdio.h>
double computeTTR(char* text); // ran out of time so it doesn't really work but my thought process is laid out, most of my confusion comes from tracking what value should be a pointer, what should it point to, and how to use that to solve the problem
int numberOfTotalWords(char* text);
int numberOfUniqueWords(char* text);
void copyWord(char* dst, char *src, int start, int end);
int isEqual(char* s1, char *s2);
void toLowerCaseWord(char *s);
char toLowerCase(char c);
char isSpace(char c);
double computeTTR(char* text){
	float total=numberOfTotalWords(text); // convert the int values into a float so the return function will return a float value 
	float unique=numberOfUniqueWords(text);
	return unique/total;
}
int numberOfTotalWords(char* text){
	int i=0;
        int *temp=text;
	int word;
	while (*(temp+i)!='\0'){ // run loop until the pointer reaches the null character
		text=temp;
		text+=i;
		if (isSpace(*(text+i))==1) // decide how many spaces there are, and then add one more because there will always be one more word compared to the number of spaces
			word++;
	}
	if (word==1) // account for there only being a space, i.e., ' ' 
		return 0;
	else
		return word+1;
}
int numberOfUniqueWords(char* text){
	char *temptext=text;
	char uniquewords[100][50]; // initialize function as described 
	char word[50];
	int start=0; 
	int end;
	int i=0;
	while (*(text+i)!='\0'){ // run until the pointer reaches the null character
		if (isSpace(*(text+i))==1){
			end=i;
			copyWord(word, text, start, end); // use the copy word function to set word equal to the indices
			if (isEqual(word,uniquewords)==1)
				uniquewords[i][50]=word; // if the word is unique add it to the unique words array
		}
		start=i+1;
		text=temptext;
		text+=i;
	}
	return sizeof(uniquewords); // I think return size here because words aren't being added to individual elements similar to problem 3, instead its the number of characters in each row being returned, so return the number of rows for the word count
}
void copyWord(char* dst, char *src, int start, int end){ // set the destination pointer to each character in the word, this one I'm the most lost on and it has no resemblance of working
	char *tempsrc=src;
	for (;start<end; start++){
		src=tempsrc;
		src+=start;
		dst[start]=src[start];
	}
	dst[end+1]='\0';
}
int isEqual(char* s1, char *s2){
	int i=0;
	int *temps1=s1;
	int *temps2=s2;
	while (*(s1+i)!='\0' && *(s2+i)!='\0'){ // run through all the elements, if at any point they deviate assume they are different words
		if (*(s1+i)!=*(s2+i))
			return 0;
	s1=temps1;
	s2=temps2;
	i++;
	}
	return 1;
}
void toLowerCaseWord(char *s){ // use ASCII to determine if a character is uppercase, and if it is send it to the lowercase function
	int i=0;
	while (*(s+i)!='\0'){
		if (*(s+i)>=65 && *(s+i)<=90)
			s=toLowerCase(*s);
		i++;
	}
}
char toLowerCase(char c){
	char lowercase=c-32; // ASCII uppercase and lowercase are 32 values apart, so any letter subtracted by 32 will result in it's lowercase version
	return lowercase;
}
char isSpace(char c){ // if the character is a space return 1
	if (c==' ')
		return 1;
	else
		return 0;
}
