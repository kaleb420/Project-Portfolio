#include <stdio.h>
#include <stdlib.h>
#include "a5problem1.h"
int main(void){
	const char *name="Alice";
	int age;
	struct person *p=createPerson();
	p->name=(char *)malloc(sizeof(char)*20);
	// setName(p,name);
	setAge(p, age);
	getName(p);
	getAge(p);
}
