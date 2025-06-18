#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "a5problem1.h"
void setName(struct person *p, const char *name){
	int length=strlen(name);
	p->name=malloc(sizeof(char)*(length+1));
	strcpy(p->name,name);
}
void setAge(struct person *p, int age){
	p->age=age;
}
char *getName(const struct person *p){
	return p->name;
}
int getAge(const struct person *p){
	return p->age;
}
struct person * createPerson(void){
        struct person *p=malloc(sizeof(struct person));
        p->setName=setName;
        p->setAge=setAge;
        p->getName=getName;
        p->getAge=getAge;
        return p;
}
