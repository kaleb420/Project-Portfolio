#include <stdio.h>
#include "a5problem1.h"
int main(void){
	struct person *p;
	const char *name;
	int age;
	createPerson();
	setName(p,name);
	setAge(p, age);
	getName(p);
	getAge(p);
}
