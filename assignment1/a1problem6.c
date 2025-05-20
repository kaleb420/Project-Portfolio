#include <stdio.h>
int main (void){
	int number;
	int i=3;
	if (number==0 || number==1){
		printf("%d has no prime factorization", number);
	}
	while (number%2==0){
		printf("2 ");
		number=number/2;
	}
	while (i%number==0){
		printf("%d ", i);
		number=number/i;
		i=i+2;
	}
	if (number>2){
		printf("%d ", number);
	}
}
