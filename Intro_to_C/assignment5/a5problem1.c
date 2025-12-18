#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "a5problem1.h"
void setName(struct person *p, const char *name){
	int length=strlen(name); // find the length of the string
	p->name=malloc(sizeof(char)*(length+1)); // add one to malloc to allocate space for the null character so strcpy works as intended
	strcpy(p->name,name); // copy the given name to the struct name via a pointer pointing to the struct
}
void setAge(struct person *p, int age){
	p->age=age; // same as the copy in set name, but this time we can set it directly equal to the variable because we are not working with string values 
}
char *getName(const struct person *p){
	return p->name; // return the value of name in struct by using a pointer that references the value
}
int getAge(const struct person *p){
	return p->age; // same as last
}
struct person * createPerson(void){
        struct person *p=malloc(sizeof(struct person)); // allocate memory the size of struct to ensure all variables can fit
        p->setName=setName; // set the pointers initialized in the struct to functions in this file
        p->setAge=setAge;
        p->getName=getName;
        p->getAge=getAge;
        return p; // return the struct
}
