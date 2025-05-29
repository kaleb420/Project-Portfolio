#include <stdio.h>
#include <math.h>
unsigned long long int factorial(long n){
	long f=1;
	int counter=1;
	while (counter<=n){
		f*=counter;
		counter++;
	}
	return f;
}
unsigned long long int subfactorial(unsigned long long int n){
	if (n==0)
		return 1;
	else if(n==1)
		return 0;
	else 
		return (n-1)*(subfactorial(n-1)+subfactorial(n-2));
}
unsigned long long int subfactorialIterative(long n){
	float subfact;
	for (int k=0; k<=n; k++){
		subfact+=pow(-1,k)/factorial(k);
	}
	subfact=subfact*factorial(n);
	return subfact;
}
