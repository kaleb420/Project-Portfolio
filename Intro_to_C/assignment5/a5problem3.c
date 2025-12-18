#include <stdio.h>
#include <string.h>
#include <stdlib.h>
unsigned int pack(char a, char b, char c, char d){
	unsigned int combined;
	int int_a=(int)a; // set each character equal to an ASCII int
	int int_b=(int)b;
	int int_c=(int)c;
	int int_d=(int)d;
	combined= (int_a << 24) | (int_b << 16) | (int_c << 8) | int_d; // to change to a 32-bit integer, and each character contains 8 bits, left shift each int by an appropriate amount to create a combined 32-bit integer, | is used to add each element to each other, as none of them will overlap based on this construction
	return combined;
}
char *unpack(unsigned int x){
	unsigned int a1, a2, b1, b2, c1, c2, d1, d2;
	char a,b,c,d;
	char *string=(char *)malloc(sizeof(char)*4); 
	int mask=15;
	a1= (mask << 28) & x; // this is inherently the same concept as the last but backwards, the issue arrises when the bits need to be right shifted but data cannot be lost, to save data, I use a mask instead, gathering the data, and then shifting back to the appropriate amount, 15 was chosen because in binary it is 1111, and then I collected 4 bits at a time to construct the first and second half of the character
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
	a= (char)(a1 << 4) | a2; // shifting the first half of the bits back to the left by 4 and adding a | constructs a new 8 bit number, associated with an ASCII character
	b= (char)(b1 << 4) | b2;
	c= (char)(c1 << 4) | c2;
	d= (char)(d1 << 4) | d2;
	strncat(string, &a, 1); // use this function to concatanate the characters created into the combined string
	strncat(string, &b, 1);
	strncat(string, &c, 1);
	strncat(string, &d, 1);
	return string;
}
