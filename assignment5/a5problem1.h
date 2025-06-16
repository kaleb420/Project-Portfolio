struct person{
	char *name;
	int age;
	void (*setName)(struct person *p, const char *name);
	void (*setAge)(struct person *p, int age);
	char (*getName)(const struct person *p);
	int (*getAge)(const struct person *p);
};
