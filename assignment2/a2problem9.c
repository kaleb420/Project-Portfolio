#include <math.h>
int power(int base, int expt){
	if (expt==0) // any number to the power of 0 is 1
		return 1;
	if (expt<=1) // if the power is 1 return the current number 
		return base;
	else
		return base*power(base, expt-1); // if the power is more than 1 return the base times itself with power-1
}
