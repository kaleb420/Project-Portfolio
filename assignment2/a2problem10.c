#include <stdio.h>
unsigned long long int fibonacciIterative(unsigned long long int n){
	int n1=0;
	int n2=1;
	printf("%d", n1); // print is used to establish the first number in the fibonacci sequence, which will always be 0
	while (n1+n2<=n){
		printf("%d", n2); // print is used to establish every subsequent number
		n2=n2+n1; // compute the next fibonacci number
		n1=n2-n1; // set n1 equal to what n2 was equal to 
	}
	if (n<=0)
		return 0;
	else
		return n2;
}
unsigned long long int fibonacciRecursive(int n){
	if (n==1 || n==0) // formula given in lecture video
		return n;
	else
		return fibonacciRecursive(n-1) + fibonacciRecursive(n-2);
}
