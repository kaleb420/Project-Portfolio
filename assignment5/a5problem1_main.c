#include <stdio.h>
#include <stdlib.h>
#include "a5problem1.h"
int main(void){
	const char *name="Alice";
	int age=50;
	struct person *p;
	createPerson();
	p->setName(p,name);
	p->setAge(p, age);
	p->getName(p);
	p->getAge(p);
}
