#include <stdio.h>
int countFunctionCalls(void){
	static int static_count=0;
	static_count++;
	return static_count;
}
unsigned long long int calculateFactorial(unsigned long long int n){
	countFunctionCalls();
	if (n<=1)
		return 1;
	else
		return n*calculateFactorial(n-1);
}
