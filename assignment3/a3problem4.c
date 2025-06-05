#include <stdio.h>
int checkSet(int *input, int input_length){
	if (input_length==0)
		return 0;
	else 
		return 1;
}
int findIntersection(int *input1, int input1_length, int *input2, int input2_length){
	int similar_elements=0; 
	int *temp1=input1;
	int *temp2=input2;
	for (int i=0; i<input1_length; i++){
		for (int j=0; j<input2_length; j++){
			input1=temp1;
			input2=temp2;
			input1+=i;
			input2+=j;
			if (*input1==*input2)
				similar_elements++;
		}
	}
	return similar_elements;
}
int findUnion(int *input1, int input1_length, int *input2, int input2_length){
	int *temp1=input1;
	int *temp2=input2;
	int a_length=input1_length+input2_length;
	int a[a_length];
	int in_a;
	int unique_count=0;
	for (int i=0; i<input1_length; i++){
		input1=temp1;
		input1+=i;
		a[i]=*input1;
	}
	for (int j=input1_length; j<input1_length+input2_length; j++){
		input2=temp2;
		input2+=j-input1_length;
		a[j]=*input2;
	}
	for (int k=0; k<a_length; k++){
		in_a=0;
		for (int p=0; p<k; p++){
			if (a[k]==a[p]){
				in_a=1;
				break;
			}
		}
		if (in_a==0)
			unique_count++;
	}
	return unique_count;
}
float calculateJaccard(int *input1, int input1_length, int *input2, int input2_length){
	return findIntersection(input1, input1_length, input2, input2_length)/findUnion(input1, input1_length, input2, input2_length);
}
