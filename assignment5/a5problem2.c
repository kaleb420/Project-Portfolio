#include <stdio.h>
#include <stdlib.h>
#include "string.h"
#include "a5problem2.h"
struct Student *readFile(const char *file){
	FILE *fPtr=fopen(file, "r");
	struct Student *head=NULL;
	struct Student *traversal;
	int number;
	char name[50];
	float marks;
	while (fscanf(fPtr, "%d %s %f", &number, name, &marks)==3){	
		struct Student *new_student=malloc(sizeof(struct Student));
		if (head==NULL){
			head=new_student;
			traversal=new_student;
		}
		else if (new_student==NULL)
			break;
		else{
			traversal->next=new_student;
			new_student->next=NULL;
			traversal=new_student;
		}
		traversal->rollNumber=number;
		strcpy(traversal->name, name);
		traversal->marks=marks;
	}
	fclose(fPtr);
	struct Student *s=head;
	return head;
}
void displayAllRecords(struct Student *head){
	struct Student *temporary=head;
	while (temporary!=NULL){
		printf("%d %s %.1f\n", temporary->rollNumber, temporary->name, temporary->marks);
		temporary=temporary->next;
	}
}
struct Student *searchStudent(struct Student *s, int rollNumber){
	struct Student *temp=s;
	while (temp->next!=NULL){
		if (temp->rollNumber==rollNumber)
			return s;
		temp=temp->next;
	}
	return NULL;
}
void addStudent(struct Student *s, int rollNumber, char *name, float marks){
	struct Student *added_student=malloc(sizeof(struct Student));
	struct Student *temp=s;
	while (temp->next!=NULL){
		temp=temp->next;
	}
	temp->next=added_student;
	added_student->rollNumber=rollNumber;
	strcpy(added_student->name, name);
	added_student->marks=marks;
	added_student->next=NULL;
}
void deleteStudent(struct Student *s, int rollNumber){
	struct Student *temp=s;
	struct Student *delete;
	while (temp->next!=NULL && temp->next->rollNumber!=rollNumber){
		temp=temp->next;
	}
	if (temp->next==NULL){ // delete last node 
		delete=temp;
		free(delete);
		delete=NULL;
	}
	else if (temp->next->rollNumber==rollNumber){ // delete any middle node
		delete=temp->next;
		temp->next=temp->next->next;
		free(delete);
		delete=NULL;
	}
	else if (s->rollNumber==rollNumber){ // delete the first node
		delete=s;
		s=temp->next;
		free(delete);
		delete=NULL;
	}
}
void writeFile(struct Student *head, const char *file){
	FILE *fPtr=fopen(file, "w");
	struct Student *temp=head;
	while (temp!=NULL){
		fprintf(fPtr,"%d %s %.1f\n", temp->rollNumber, temp->name, temp->marks);
		temp=temp->next;
	}
}
