#include <stdio.h>
unsigned long long int fibonacciIterative(int n){
	int n1=0;
	int n2=1;
	printf("%d", n1);
	while (n1+n2<=n){
		printf("%d", n2);
		n2=n2+n1;
		n1=n2-n1;
	}
	if (n<=0)
		return 0;
	else
		return n2;
}
unsigned long long int fibonacciRecursive(int n){
	if (n==1 || n==0)
		return n;
	else
		return fibonacciRecursive(n-1) + fibonacciRecursive(n-2);
}
