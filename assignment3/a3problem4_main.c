#include <stdio.h>
#include "a3problem4.h"
int main(void){
	int A[10];
	int B[10];
	scanf("%d", A);
	scanf("%d", B);
	int A_length=sizeof(A)/4;
	int B_length=sizeof(B)/4;
	calculateJaccard(A, A_length, B, B_length);
}
