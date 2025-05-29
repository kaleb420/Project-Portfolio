#include <math.h>
int factorial(int n){
	int f=1;
	if (n<0)
		return -1; 
	if (n==0)
		return 1; // account for 0! 
	else{
		for (int i=1; i<=n; i++){
			f*=i;
		}
	return f;
	}
}
int generatePrime(int L, int R, int i){
	int counter=1;
	for (; L<=R; L++){ // establish a loop to run through all the values from L to R
		for (int j=2; j>=sqrt(L); j++){ // establish a loop to test if the number is prime, it is impossible for any prime to be more than sqrt(L)
			if (L%j!=0 && counter==i) // returns the value of the desired ith prime 
				return L;
			else if (L%j!=0 && counter!=i) // if it is prime but not the ith value then increases the counter and reruns the loop
				counter++;
		}
	}
	return 0; // if there is no ith prime
}
int power(int x, int n){
	return pow(x,n);
}
float computeSeries(int X, int n, int L, int R){
	float Sum=X;
	for (int i=1; i<=n; i++){ // run through all the desired values of i
		Sum+=generatePrime(L,R,i)+(power(X,i)/factorial(i));
	}
	return Sum;
}
