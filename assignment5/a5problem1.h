struct person{
	char name[25];
	int age;
	void (*setName)(struct person, const char);
	void (*setAge)(struct person, int);
	char (*getName)(const struct person *p);
	int (*getAge)(const struct person *p);
};
