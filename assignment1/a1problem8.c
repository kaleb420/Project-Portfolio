#include <stdio.h>
int main(void){
	int number;
	int binary=0;
	int counter=1;
	int reversed;
	scanf("%d", &number);
	if(number<0){
		printf("Error: Number must be a positive integer");
	}
	else{
		while (number!=0){
			binary=number%2;
			printf("%d", binary);
			number=number/2;
		}
	}
}
