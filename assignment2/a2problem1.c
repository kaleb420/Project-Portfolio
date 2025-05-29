#include <math.h>
int factorial(int n){ // anything intuitive (for example, function definitions, repeated processes throughout the homework, or following formulas given) will most often not be commented upon
	int f=1;
	for (int i=1; i<=n; i++){ // calculate factorial using a loop
		f*=i;
	}
	return f;
}
float binomialDistribution(int k, int n, float p){ // calculate binomial distribution based on N questions with c choices
	float bd=(factorial(n)/(factorial(k)*factorial(n-k)))*pow(p,k)*pow(1.0-p,n-k); 
	return bd;
}
float probabilityOfPassingMCQTest(int N, int c){
	float p=1.0/c; // establish probability of randomly guessing correctly
	float Ps=0;
	int k=ceil(N*.6); // ceil used to round up the questions needed to pass
	for (; k<=N; k++){
		Ps+=binomialDistribution(k,N,p);
	}
	return Ps;
}
