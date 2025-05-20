#include <stdio.h>
#include <math.h>
int main(void){
	double num1;
	double num2;
	char operator;
	switch(operator){
		case '+':
			printf("%.3f\n", num1+num2);
		case '-':
			printf("%.3f\n", num1-num2);
		case '*':
			printf("%.3f\n", num1*num2);
		case '/':
			if (num2!=0){
				printf("%.3f\n", num1 / num2);
			}
			else{
				printf("Error: dividing by zero\n");
			}
		case '%':
			printf("%.3f\n", fmod(num1, num2));
		case '^':
			printf("%.3f\n", pow(num1,num2));
		case 'r':
			if (fmod(num1,2)==0 && num2<0){
				printf("Error: even root of negative number\n");
			}
			else{
				printf("%.3f\n", pow(num2,1.0/num1));
			}
}}
