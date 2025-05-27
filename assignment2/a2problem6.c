#include <stdio.h>
int sumOfSquares(int n){
	int Sum;
	char strn[5];
	sprintf(strn, "%d", n);
	for (int i=0; i<5; i++){
		Sum+=strn[i]*strn[i];
	}
	return Sum;
}
int happy2Help(int n, int depth){
	if (n==1)
		return 1;
	else if (depth>=100)
		return 0;
	else{
		depth++;
		return sumOfSquares(n);
	}
}
int isHappy(int n){
	int h=happy2Help(n,0);
	if (h==1)
		return 1;
	else
		return 0;
}
