#include <stdio.h>
void mergeArrays(int A[], int size_a, int B[], int size_b, int C[]){
	int a_index=0, b_index=0, size_c=size_a+size_b;
	for (int k=0; k<size_c; k++){ // loop through the length of C[] to assign C[k] an appropriate value
		if (A[a_index]>=B[b_index] && b_index<size_b){ // calculate which one is less if the index is not pass the array size
			C[k]=B[b_index];
			b_index++;
		}
		else if (A[a_index]<B[b_index] && a_index<size_a){ // same as last comment but with a being the lesser value
			C[k]=A[a_index];
			a_index++;
		} 
		else if (a_index<size_a){ // this is done to prevent accessing a non existant integer in an array, for example, A[100] would likely result in some garbage value, inhibiting the program, if it gets to this point it can be assumed there is only one array with valid indices left, so it is unecessary to check the values 
			C[k]=A[a_index];
			a_index++;
		}
		else if (b_index<size_b){ // same with previous comment but with b being the array with indices left
			C[k]=B[b_index];
			b_index++;
		}
	}
	for (int i=0; i<size_a+size_b; i++){ // loop through the indices of C to print the values 
		printf("%d\n", C[i]);
	}
}
