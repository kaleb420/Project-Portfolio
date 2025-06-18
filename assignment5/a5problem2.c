#include <stdio.h>
#include <stdlib.h>
#include "string.h"
#include "a5problem2.h"
struct Student *readFile(const char *file){
	FILE *fPtr=fopen(file, "r"); // open file
	struct Student *head=NULL;
	struct Student *traversal;
	int number;
	char name[50];
	float marks;
	while (fscanf(fPtr, "%d %s %f", &number, name, &marks)==3){ // this will run until three values on a line are not found
		struct Student *new_student=malloc(sizeof(struct Student)); // initialize a new student for each iteration
		if (head==NULL){ // set the head pointer if there is none 
			head=new_student;
			traversal=new_student; // set the traversal equal to the same thing
		}
		else if (new_student==NULL) // edge case along with being used for testing purposes, if somehow the while loop continues even if there are not three variables, looking at it again it probably doesn't do anything cause new_student will never be NULL even if the loop improperly continues
			break;
		else{
			traversal->next=new_student; // set the next pointer to the new student created
			new_student->next=NULL; // set the next pointer after new_student to NULL to properly initialize the linked list
			traversal=new_student; // set the traversal equal to the new student created to properly traverse the linked list
		}
		traversal->rollNumber=number; // set the traversal rollNumber to the number scanned, traversal changes each time so it depends what iteration the linked list is at for what values are being set where 
		strcpy(traversal->name, name); // set the traversal name to the name scanned
		traversal->marks=marks; // set the traversal marks to the marks scanned
	}
	fclose(fPtr); // close the file
	struct Student *s=head; // define the s variable used in future functions 
	return head;
}
void displayAllRecords(struct Student *head){
	struct Student *temporary=head; // set a temporary node to head to not cause issues manipulating the pointer
	while (temporary!=NULL){ // last member of the linked list will be NULL
		printf("%d %s %.1f\n", temporary->rollNumber, temporary->name, temporary->marks); // print the values
		temporary=temporary->next; // set the temporary value to the next list
	}
}
struct Student *searchStudent(struct Student *s, int rollNumber){
	struct Student *temp=s;
	while (temp->next!=NULL){ // if it reaches NULL then the student is not found
		if (temp->rollNumber==rollNumber) // if the rollNumber in the current list is equal to the roll number desired return the list it was found
			return s;
		temp=temp->next;
	}
	return NULL;
}
void addStudent(struct Student *s, int rollNumber, char *name, float marks){
	struct Student *added_student=malloc(sizeof(struct Student));
	struct Student *temp=s;
	while (temp->next!=NULL){ // as per instructions add the new student to the last list
		temp=temp->next;
	}
	temp->next=added_student; // set the list to the memory addressed stored by added student
	added_student->rollNumber=rollNumber; // set the values equal to the appropriate struct location, similar in the read file function
	strcpy(added_student->name, name);
	added_student->marks=marks;
	added_student->next=NULL; // set the next list to NULL
}
void deleteStudent(struct Student *s, int rollNumber){
	struct Student *temp=s;
	struct Student *delete; // store a delete pointer that will be deleted once found
	while (temp->next!=NULL && temp->next->rollNumber!=rollNumber){ // iterate through the list until it reaches the end of the list or the next node equals the appropriate roll number
		temp=temp->next;
	}
	if (temp->next==NULL){ // delete last list
		delete=temp; // set delete equal to temp
		free(delete); // free the allocated memory
		delete=NULL; // set the delete list to NULL
	}
	else if (temp->next->rollNumber==rollNumber){ // delete any middle list
		delete=temp->next; // set the node to be deleted to the next list, this is done to link the current list to two lists ahead
		temp->next=temp->next->next; 
		free(delete);
		delete=NULL; // same concept as last if 
	}
	else if (s->rollNumber==rollNumber){ // delete the first list
		delete=s;
		s=temp->next; // set the header to the next list
		free(delete);
		delete=NULL; // same concept as last if 
	}
}
void writeFile(struct Student *head, const char *file){
	FILE *fPtr=fopen(file, "w"); // open the file to write in it
	struct Student *temp=head;
	while (temp!=NULL){
		fprintf(fPtr,"%d %s %.1f\n", temp->rollNumber, temp->name, temp->marks); // use fprintf to write lines in the new file
		temp=temp->next;
	}
	fclose(fPtr); // close the file to save changes
}
