#include <stdio.h>
unsigned int add(unsigned int x, unsigned int y){
	unsigned int bits;
	while (y!=0){ // y will eventually equal 0 because & is going to run out of bits to add after all the bits have been added already
		bits=y&x; // check for matching values
		bits= bits << 1; // those values effectively double due to how binary is set up
		x= x ^ y; // check for unique values which are added to x, the total sum in this case
		y=bits; // set y equal to the matching values previously found 
	}
	return x;
}
