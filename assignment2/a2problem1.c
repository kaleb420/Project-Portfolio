#include <math.h>
int factorial(int n){
	int f=1;
	for (int i=1; i<=n; i++){
		f*=i;
	}
	return f;
}
float binomialDistribution(int k, int n, float p){
	float bd=(factorial(n)/(factorial(k)*factorial(n-k)))*pow(p,k)*pow(1.0-p,n-k);
	return bd;
}
float probabilityOfPassingMCQTest(int N, int c){
	float p=1.0/c;
	float Ps=0;
	int k=ceil(N*.6);
	for (; k<=N; k++){
		Ps+=binomialDistribution(k,N,p);
	}
	return Ps;
}
