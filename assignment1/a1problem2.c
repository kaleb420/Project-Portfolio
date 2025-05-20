#include <stdio.h>
int main(void){
	int a1;
	int a2;
	int a3;
	char *validity;
	char *type;
	char *isosceles;
	char *equilateral;
	scanf("%d", &a1);
	scanf("%d", &a2);
	scanf("%d", &a3);
	if(a1+a2+a3==180 && a1>0 && a2>0 && a3>0){
		validity="valid";
		if(a1>90 || a2>90 || a3>90){
			type="obtuse";
		}
		else if(a1<90 && a2<90 && a3<90){
			type="acute";
		}
		else if(a1==90 || a2==90 || a3==90){
			type="right";
		}
		if(a1==a2 || a1==a3 || a2==a3){
			isosceles="isosceles";
			if(a1==a2 && a2==a3){
				equilateral="equilateral";
			}
			else{
				equilateral="";
			}
		}
		else{
			isosceles="";
			equilateral="";
		}
	printf("%s %s %s %s", validity, type, isosceles, equilateral);
	}
	else{
		validity="invalid";
		printf("%s", validity);
	}
}
