#include <stdio.h>
int main(void){
	int number;
	printf("Enter the starting Fizz Buzz number: ");
	scanf("%d", &number);
	if(number<=0){
		printf("Error: cannot compute Fizz Buzz of %d", number);
	}
	else{
		while(number>=1){
			if (number%4==0){
				if(number%3==0 && number%5==0){
					printf("Fizz-Buzz\n ");
					number--;
				}
				else if(number%3==0){
					printf("Fizz\n ");
					number--;
				}
				else if(number%5==0){
					printf("Buzz\n ");
					number--;
				}
				else{
					printf("%d\n ", number);
					number--;
				}
			}
			else{
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
}
