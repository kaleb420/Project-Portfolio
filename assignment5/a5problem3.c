#include <stdio.h>
#include <string.h>
#include <stdlib.h>
unsigned int pack(char a, char b, char c, char d){
	unsigned int combined;
	int int_a=(int)a;
	int int_b=(int)b;
	int int_c=(int)c;
	int int_d=(int)d;
	combined= (int_a << 24) | (int_b << 16) | (int_c << 8) | int_d;
	return combined;
}
char *unpack(unsigned int x){
	unsigned int a1, a2, b1, b2, c1, c2, d1, d2;
	char a,b,c,d;
	char *string=(char *)malloc(sizeof(char)*4);
	int mask=15;
	a1= (mask << 28) & x;
	a1= a1 >> 28;
	a2= (mask << 24) & x;
	a2= a2 >> 24;
	b1= (mask << 20) & x;
	b1= b1 >> 20;
	b2= (mask << 16) & x;
	b2= b2 >> 16;
	c1= (mask << 12) & x;
	c1= c1 >> 12;
	c2= (mask << 8) & x;
	c2= c2 >> 8;
	d1= (mask << 4) & x;
	d1= d1 >> 4; 
	d2= mask & x;
	a= (char)(a1 << 4) | a2;
	b= (char)(b1 << 4) | b2;
	c= (char)(c1 << 4) | c2;
	d= (char)(d1 << 4) | d2;
	strncat(string, &a, 1);
	strncat(string, &b, 1);
	strncat(string, &c, 1);
	strncat(string, &d, 1);
	return string;
}
