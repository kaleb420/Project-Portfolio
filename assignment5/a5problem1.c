#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "a5problem1.h"
struct person * createPerson(void){
	struct person *p=(struct person *)malloc(sizeof(struct person));
	return p;
}
void setName(struct person *p, const char *name){
	int length=strlen(name);
	char *buffer=(char *)malloc(length*sizeof(char));
	strcpy(buffer,name);
	strcpy(p->name, buffer);
	free(buffer);
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
