#include <stdio.h>
#include "a3problem6.h"
#define NUM_ROWS 256
#define NUM_COLS 256
int main(void){
	int img[NUM_ROWS][NUM_COLS][3];
	zero_blue(img);
	invert(img);
	rotate_clockwise(img);
}
