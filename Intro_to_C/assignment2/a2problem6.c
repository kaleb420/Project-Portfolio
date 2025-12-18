#include <stdio.h>
int sumOfSquares(int n){
	if (n!=0){
		return (n%10)*(n%10)+sumOfSquares(n/10); // performing n%10 provides the value of the first digit, squaring that and returning the value divided by 10 returns the next digit until there is no more digits left 
	}
	else
		return 0;
}
int Happy2Help(int n, int depth){
	while (depth<=100){ // after the previous function establishes the sum of square value, this function analyizes if the number equals 1, if it is the loop is ended, if not the function is called again with a depth of +1
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
	int h=Happy2Help(n,0); // this function is here upon instructions, although there is no obvious purpose 
	if (h==1)
		return 1;
	else
		return 0;
}
