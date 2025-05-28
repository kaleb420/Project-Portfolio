#include <stdio.h>
int sumOfSquares(int n){
	if (n!=0){
		return (n%10)*(n%10)+sumOfSquares(n/10);
	}
	else
		return 0;
}
int Happy2Help(int n, int depth){
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
