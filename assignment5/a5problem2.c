#include <stdio.h>
#include <stdlib.h>
#include "string.h"
#include "a5problem2.h"
struct Student *readFile(const char *file){
	FILE *fPtr=fopen(file, "r");
	struct Student *head=fPtr;
	return head;
}
void displayAllRecords(struct Student *head){
	struct Student *temporary=head;
	while (temporary!=NULL){
		printf("%d, %s, %.1f\n", temporary->rollNumber, temporary->name, temporary->marks);
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
void addStudent(struct Student*s, int rollNumber, char *name, float marks){
	struct Student *added_student=(struct Student *)malloc(sizeof(struct Student));
	while (s->next!=NULL){
		s=s->next;
	}
	s->next=added_student;
	added_student->rollNumber=rollNumber;
	strcpy(added_student->name, name);
	added_student->marks=marks;
}
void deleteStudent(struct Student *s, int rollNumber){
	while (s->next->rollNumber!=rollNumber){
		s=s->next;
	}
	struct Student *delete=s->next;
	s->next=s->next->next;
	free(delete);
	delete=NULL;
}
void writeFile(struct Student *head, const char *file){
	FILE *fPtr=fopen(file, "r");
	struct Student *temp=head;
	while (temp!=NULL){
		printf("%d, %s, %.1f\n", temp->rollNumber, temp->name, temp->marks);
		temp=temp->next;
	}
}
