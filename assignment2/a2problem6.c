#include <stdio.h>
int sumOfSquares(int n){
	int Sum=0;
	int digit;
	while (n>0){
		digit=n%10;
		n=n/10;
		Sum+=digit*digit;
	}
	return Sum;
}
int Happy2Help(int n, int depth){
	depth=0;
	while (depth<=100){
		if (n==1)
			return 1;
		else{
			depth++;
			return Happy2Help(sumOfSquares(n), depth);
		}
	}
	return 0;
}
int isHappy(int n){
	int h=Happy2Help(n,0);
	if (h==1)
		return 1;
	else
		return 0;
}
