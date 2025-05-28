#include <stdio.h>
void collatz(int n){
	if (n==1){
		return;
	}
	if (n%2==1){
		return collatz(3*n+1);
	}
	else if (n%2==0){
		return collatz(n/2);
	}
}
void collatzIterative(int n){
	while (n!=1){
		printf("%d,", n);
		if (n%2==1){
			n=3*n+1;
		}
		else if (n%2==0){
			n=n/2;
		}
	}
	printf("%d",n);
	return;
}
