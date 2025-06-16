struct Student{
	int rollNumber;
	char name[50];
	float marks;
	struct Student *next;
};
struct Student *readFile(const char *file);
void displayAllRecords(struct Student *head);
struct Student *searchStudent(struct Student *s, int rollNumber);
void addStudent(struct Student *s, int rollNumber, char *name, float marks);
void deleteStudent(struct Student *s, int rollNumber);
void writeFile(struct Student *head, const char *file);
