#include <stdio.h>
unsigned int add(unsigned int x, unsigned int y){
	unsigned int bit;
	while (y!=0){
		bit=x&y;
		bit=bit^x;
		y=y<<1; 
	}
	return bit;
}
