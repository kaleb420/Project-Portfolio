#include <stdio.h>
#include "a3problem3.h"
int main(void){
	char input[20];;
	int k;
	scanf("%s", input);
	scanf("%d", &k);
	int le=length(input);
	int n=le-k+1;
	char kGrams[n][k+1];
	generateKGrams(k, le, n, input, kGrams);
}
