#include <stdio.h>
void mergeArrays(int A[], int size_a, int B[], int size_b, int C[]){
	int a_index=0, b_index=0, size_c=size_a+size_b;
	for (int k=0; k<size_c; k++){
		if(A[a_index]>=B[b_index] && a_index>size_a){
			C[k]=A[a_index];
			a_index++;
		}
		else if (A[a_index]<B[b_index] && b_index>size_b){
			C[k]=B[b_index];
			b_index++;
		}
	}
	printf("%ls", C);
}
