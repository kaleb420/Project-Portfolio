#include <stdio.h>
int countFunctionCalls(void){ // count how many times the function ran using a static variable, so the function being called again does not change the value 
	static int static_count=0;
	static_count++;
	return static_count;
}
unsigned long long int calculateFactorial(unsigned long long int n){
	countFunctionCalls(); // call the function to increase the counter 
	if (n<0) // account for negative factorials 
		return -1;
	if (n<=1) // base case
		return 1;
	else
		return n*calculateFactorial(n-1); // recursive case
}
