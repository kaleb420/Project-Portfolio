#include <stdio.h>
int main(void){
	char Type;
	float MSalary;
	float MOvertime;
	float DOvertime;
	char Vacation;
	int Sold;
	float WSalary;
	float WOvertime;
	float HSalary;
	float WHours;
	char Married;
	float Annual;
	float Tax;
	int OLimit;
	int OType;
	scanf(" %c", &Type);
	if(Type=='A'){
		scanf("%f", &MSalary);
		OLimit=10000000;
		WSalary=1;
		HSalary=1;
		Annual=MSalary*12;
	}
	else if(Type=='S'){
		scanf("%f", &MSalary);
		scanf("%f", &MOvertime);
		OLimit=10;
		OType=MOvertime;
		if (OLimit < MOvertime){
			Annual=MSalary*12+(MSalary/4/5/8)*10*1.5*12;
		}
		else{
			Annual=MSalary*12+(MSalary/4/5/8)*MOvertime*1.5*12;
	}}
	else if(Type=='E'){
		scanf("%f", &MSalary); 
		scanf("%f", &DOvertime);
		scanf(" %c", &Vacation);
		scanf("%d", &Sold);
		OLimit=1;
		OType=DOvertime;
		if (OLimit < DOvertime){
			if (Vacation=='y'){
				Annual=MSalary*11+1*4*5*11*1.35+600*Sold+.5*MSalary;
			}
			else if (Vacation=='n'){
				Annual=MSalary*12+1*4*5*12*1.35+600*Sold;
			}
		}
		else {
			if (Vacation=='y'){
				Annual=MSalary*11+DOvertime*4*5*11*1.35+600*Sold+.5*MSalary;
			}
			else if (Vacation=='n'){
				Annual=MSalary*12+DOvertime*4*5*12*1.35+600*Sold;
			}
	}}
	else if(Type=='P'){
		scanf("%f", &WSalary);
		scanf("%f", &WOvertime);
		scanf("%d", &Sold);
		OLimit=10;
		OType=WOvertime;
		if (OLimit < WOvertime){
			Annual=WSalary*4+10*4+600*Sold;
		}
		else {
			Annual=WSalary*4+WOvertime*4+600*Sold;
	}}
	else if(Type=='H'){
		scanf("%f", &HSalary);
		scanf("%f", &WHours);
		OLimit=20;
		OType=WOvertime;
		if (WHours>10){
			Annual=10*HSalary*4*12+((WHours-10)*HSalary*1.25*4*12);
		}
		else if(WHours<=10){
			Annual=10*HSalary*4*12;
		}
	}
	scanf(" %c", &Married);
	if (Married=='y'){
		if (Annual>32000){
			Tax=Annual*.25;
		}
		else if(Annual<=32000){
			Tax=Annual*.10;
		}
	}
	else if (Married=='n'){
		if (Annual>64000){
			Tax=Annual*.25;
		}
		else if (Annual<=64000){
			Tax=Annual*.10;
		}
	}
	if (MSalary<0 || WSalary<0 || HSalary<0){
		printf("Error: Salary cannot be negative.");
	}
	else{
		if (OLimit < OType){
			printf("Overtime hours exceed limit. Only %d hours will be counted.\n", OLimit);
		}
		printf("This employee's annual income is $%.3f before tax and $%.3f after tax\n", Annual, Annual-Tax);

	}
}
