#include <stdio.h>
#include <stdlib.h>
#include "a5problem1.h"
int main(void){
	const char *name;
	int age;
	struct person *p;
	createPerson();
	p->setName(p,name); // point towards the function that should be called 
	p->setAge(p, age);
	p->getName(p);
	p->getAge(p);
}
