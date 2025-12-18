#include <stdio.h>
#include "a3problem1.h"
#define size_a 20
#define size_b 20
int main(void){
	int A[size_a], B[size_b], C[size_a+size_b];
	mergeArrays(A, size_a, B, size_b, C);
}
