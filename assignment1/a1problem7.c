#include <stdio.h>
int main(void){
	int number;
	if(number<0){
		printf("Error: cannot compute Fizz Buzz of %d", number);
	}
	else{
		while(number>=1){
			if(number%3==0 && number%5==0){
				printf("Fizz-Buzz ");
				number--;
			}
			else if(number%3==0){
				printf("Fizz ");
				number--;
			}
			else if(number%5==0){
				printf("Buzz ");
				number--;
			}
			else{
				printf("%d ", number);
				number--;
			}
		}
	}
}
