#include <stdio.h>
#include <math.h>
int main(void){
	double num1;
	double num2;
	char operator;
	scanf("%lf  %c %lf", &num1, &operator, &num2);
	switch(operator){
		case '+':
			printf("%.3f", num1+num2);
			break;
		case '-':
			printf("%.3f", num1-num2);
			break;
		case '*':
			printf("%.3f", num1*num2);
			break;
		case '/':
			if (num2!=0){
				printf("%.3f", num1 / num2);
				break;
			}
			else{
				printf("Error: dividing by zero");
				break;
			}
		case '%':
			printf("%.3f", fmod(num1, num2));
			break;
		case '^':
			printf("%.3f", pow(num1,num2));
			break;
		case 'r':
			if (fmod(num1,2)==0 && num2<0){
				printf("Error: even root of negative number");
				break;
			}
			else if (fmod(num1,2)==1 && num2<0){
					printf("%.3f", -pow(-num2,1.0/num1));
					break;
			}
			else{
				printf("%.3f", pow(num2,1.0/num1));
			}
	}
}
