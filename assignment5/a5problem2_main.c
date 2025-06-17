#include <stdio.h>
#include "a5problem2.h"
int main(void){
	const char *file="records1.txt";
	int rollNumber=12345;
	char *name="John";
	float marks=85.5;
	struct Student *head;
	struct Student *s;
	head=readFile(file);
	s=head;
	displayAllRecords(head);
	searchStudent(s, rollNumber);
	addStudent(s, rollNumber, name, marks);
	deleteStudent(s, rollNumber);
	writeFile(head, file);
}
