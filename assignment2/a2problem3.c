#include <math.h>
int factorial(int n){
	int f=1;
	if (n==0)
		return 1;
	else{
		for (int i=1; i<=n; i++){
			f*=i;
		}
	return f;
	}
}
int generatePrime(int L, int R, int i){
	int counter=1;
	for (; L<=R; L++){
		for (int j=2; j>=sqrt(L); j++){
			if (L%j!=0 && counter==i)
				return L;
			else if (L%j!=0 && counter!=i)
				counter++;
		}
	}
	return 0;
}
int power(int x, int n){
	return pow(x,n);
}
float computeSeries(int X, int n, int L, int R){
	float Sum=X;
	for (int i=1; i<=n; i++){
		Sum+=generatePrime(L,R,i)+(power(X,i)/factorial(i));
	}
	return Sum;
}
