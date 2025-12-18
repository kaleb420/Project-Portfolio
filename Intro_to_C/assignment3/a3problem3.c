#include <stdio.h>
void generateKGrams(int k, int le, int n, char input[], char kGrams[][k+1]){
	for (int i=0; i<n; i++){ // stop loop early at n to ensure kGrams isn't filled with a value passed the array's scope
		for (int j=0; j<k; j++){ // loop through all the values from 0-k
			kGrams[i][j]=input[i+j]; // this is a bit hard for me to explain but set the row to i, this allows the characters in j column to all be printed at once, for example, kGrams[0][0]='a', kGrams[0][1]='b', then the print statement down below prints the values stored within the columns for the associated row
		}
		kGrams[i][k]='\0'; // as asked set the last value equal to null
	}
	for (int p=0; p<n; p++){
		printf("%s ", kGrams[p]); // to continue the example, kGrams[0] would print "ab" 
	}
}
int length(char str[]){
	int counter=0;
	while (str[counter]!='\0'){ // the last character of the array is the null character, so once the array reaches that we know there are no more elements, counter is used to store the length value
		counter++;
	}
	return counter;
}
