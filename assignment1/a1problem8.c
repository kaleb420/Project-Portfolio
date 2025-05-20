#include <stdio.h>
int main(void){
	int number;
	int binary=0;
	int counter=1;
	int reversed;
	printf("Input number: ");
	scanf("%d", &number);
	if(number<0){
		printf("Error: Number must be a positive integer");
	}
	else{
		while (number!=0){
			if (number%2==1){
				binary=binary+(1*counter);
			}
			number=number/2;
			counter=counter*10;
		}
	}
	printf("%d", reversed);
}
