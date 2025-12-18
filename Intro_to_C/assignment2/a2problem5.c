#include <stdio.h>
void collatz(int n){
	if (n==1){
		printf("%d\n", n); // print was used to correclty form the sequence the autograder wanted, this one is lacking a comma because it will be the last n value returned, which will always be 1
		return;
	}
	printf("%d,", n);
	if (n%2==1)
		return collatz(3*n+1);
	else if (n%2==0)
		return collatz(n/2);
}
void collatzIterative(int n){
	while (n!=1){
		printf("%d,", n); // same concept as the last one excpet using a loop instead of recursion
		if (n%2==1)
			n=3*n+1;
		else if (n%2==0)
			n=n/2;
	}
	printf("%d",n);
	return;
}
