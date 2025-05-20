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
	if(Type=='A'){
		printf("Input monthly salary: ");
		scanf("%f", &MSalary);
		Annual=MSalary*12;
	}
	else if(Type=='S'){
		OLimit=10;
		OType=MOvertime;
		printf("Input monthy salary: ");
		scanf("%f", &MSalary);
		printf("Input overtime hours per month: ");
		scanf("%f", &MOvertime);
		if (OLimit < OType){
			Annual=MSalary*12+(MSalary/4/5/8)*10*1.5*12;
		}
		else{
			Annual=MSalary*12+(MSalary/4/5/8)*MOvertime*1.5*12;
	}}
	else if(Type=='E'){
		OLimit=1;
		OType=DOvertime;
		printf("Input monthly salary: ");
		scanf("%f", &MSalary);
		printf("Input overtime hours per day: ");
		scanf("%f", &DOvertime);
		printf("Is this employe going to take a vacation for a month: ");
		scanf(" %c", &Vacation);
		printf("Input number of products sold in a year: ");
		scanf("%d", &Sold);
		if (OLimit < OType){
			if (Vacation=='y'){
				Annual=MSalary*11+1*4*5*11*1.35+600*Sold+.5*MSalary;
			}
			else if (Vacation=='n'){
				Annual=MSalary*12+1*4*5*12*1.35+600*Sold;
			}
		}
		else {
			Annual=MSalary*12+DOvertime*4*5*12*1.35+600*Sold;
	}}
	else if(Type=='P'){
		OLimit=10;
		OType=WOvertime;
		printf("Input weekly salary: ");
		scanf("%f", &WSalary);
		printf("Input overtime hours per week: ");
		scanf("%f", &WOvertime);
		printf("Input number of products sold in a year: ");
		scanf("%d", &Sold);
		if (OLimit < OType){
			Annual=WSalary*4+10*4+600*Sold;
		}
		else {
			Annual=WSalary*4+WOvertime*4+600*Sold;
	}}
	else if(Type=='H'){
		OLimit=20;
		OType=WOvertime;
		printf("Input hourly salary: ");
		scanf("%f", &HSalary);
		printf("Input hours worked per week: ");
		scanf("%f", &WHours);
		if (WHours>10){
			Annual=10*HSalary*4*12+((WHours-10)*HSalary*1.25*4*12);
		}
		else if(WHours<=10){
			Annual=10*HSalary*4*12;
		}
	}
	if (MSalary<0 || WSalary<0 || HSalary<0){
		printf("Error: Salary cannot be negative.");
	}
	printf("Is this employee married: ");
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
	if (OLimit < OType){
		printf("Overtime hours exceed limit. Only %d hours will be counted", OLimit);
	}
	printf("This employee's annual income is $%.3f before tax and $%.3f after tax\n", Annual, Annual-Tax);
}
