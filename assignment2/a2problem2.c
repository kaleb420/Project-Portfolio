int countFunctionCalls(void){
	static int static_count=-1;
	static_count++;
	return  static_count;
}
int calculateFactorial(int n){
	if (n==1)
		return countFunctionCalls(), 1;
	else
		return countFunctionCalls(), n*calculateFactorial(n-1);
}
