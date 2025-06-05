#include <stdio.h>
double computeTTR(char* text);
int numberOfTotalWords(char* text);
int numberOfUniqueWords(char* text);
void copyWord(char* dst, char *src, int start, int end);
int isEqual(char* s1, char *s2);
void toLowerCaseWord(char *s);
char toLowerCase(char c);
char isSpace(char c);
double computeTTR(char* text){
	text=toLowerCase(text);
	float total=numberOfTotalWords(text);
	float unique=numberOfUniqueWords(text);
	return unique/total;
}
int numberOfTotalWords(char* text){
	int i=0;
        int *temp=text;
	int counter;
	while (i!='\0'){
		text=temp;
		text+=i;
		if (isSpace(*text)==1)
			counter++;
	}
	return counter+1;
}
int numberOfUniqueWords(char* text){
	char *temptext=text;
	char uniquewords[100][50];
	char word[50];
	int start=0; 
	int end;
	for (int i=0; i<100; i++){
		if (isSpace(*(text+1))==1){
			end=i;
			copyWord(word, text, start, end);
			if (isEqual(word,uniquewords)==1)
				uniquewords[i][50]=word;
		}
		start=i+1;
		text+=i;
	}
	return uniquewords;
}
void copyWord(char* dst, char *src, int start, int end){
	char *tempsrc=src;
	for (;start<end; start++){
		src=tempsrc;
		src+=start;
		dst[start]=src[start];
	}
	dst[end+1]='\0';
}
int isEqual(char* s1, char *s2){
	for (int i=0; i<20; i++){
		if (*(s1+i)!=*(s2+i))
			return 0;
	}
	return 1;
}
void toLowerCaseWord(char *s){
	for (int i=0; i<20; i++){
		if (*(s+i)>=65 && *(s+i)<=90)
			toLowerCase(*s);
	}
}
char toLowerCase(char c){
	char lowercase=c-32;
	return lowercase;
}
char isSpace(char c){
	if (c==' ')
		return 1;
	else
		return 0;
}
