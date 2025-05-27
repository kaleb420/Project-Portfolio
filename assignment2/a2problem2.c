int countFunctionCalls(int n){
	static int static_count=-1;
	static_count++;
	return  static_count;
}
int calculateFactorial(int n){
	if (n==1)
		return countFunctionCalls(n), 1;
	else
		return countFunctionCalls(n), n*calculateFactorial(n-1);
}
