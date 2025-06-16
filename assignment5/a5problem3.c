#include <stdio.h>
unsigned int pack(char a, char b, char c, char d){
	unsigned int combined;
	int a_a= (int)a;
	int a_b= (int)b;
	int a_c= (int)c;
	int a_d= (int)d;
	int remainder;
	remainder=a_a & ~16;
	a_a= a_a >> 4;
	a_a= (a_a << 10) ^ remainder;
	printf("%d\n", a_a);
	remainder=a_b & ~16;
	a_b= a_b >> 4;
	a_b= (a_b << 10) ^ remainder;
	remainder=a_c & ~16;
	a_c= a_c >> 4;
	a_c= (a_c << 10) ^ remainder;
	remainder=a_d & ~16;
	a_d= a_d >> 4;
	a_d= (a_d << 10) ^ remainder;
	combined= (a_a << 24) | (a_b << 16) | (a_c << 8) | a_d;
	printf("%u", combined);
	return combined;
}
char *unpack(unsigned int x){
}
