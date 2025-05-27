static int original;
int power(int base, int expt){
	original=base;
	if (expt==1)
		return base;
	else
		return power(base*original, expt-1);
}
