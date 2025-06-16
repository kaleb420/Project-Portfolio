#include <stdio.h>
#include "a5problem2.h"
int main(void){
	const char *file="records1.txt";
	int rollNumber;
	char *name;
	float marks;
	struct Student *head;
	struct Student *s;
	readFile(file);
	displayAllRecords(head);
	searchStudent(s, rollNumber);
	addStudent(s, rollNumber, name, marks);
	deleteStudent(s, rollNumber);
	writeFile(head, file);
}
