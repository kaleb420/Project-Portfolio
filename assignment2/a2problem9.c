#include <math.h>
int power(int base, int expt){
	if (expt<=1)
		return base;
	else
		return base*power(base, expt-1);
}
