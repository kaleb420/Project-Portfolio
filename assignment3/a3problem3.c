#include <stdio.h>
void generateKGrams(int k, int le, int n, char input[], char kGrams[][k+1]){
	for (int i=0; i<n; i++){
		for (int j=0; j<k; j++){
			kGrams[i][j]=input[i+j];
		}
		kGrams[i][k]='\0';
	}
	for (int p=0; p<n; p++){
		printf("%s ", kGrams[p]);
	}
}
int length(char str[]){
	int counter=0;
	while (str[counter]!='\0'){
		counter++;
	}
	return counter;
}
