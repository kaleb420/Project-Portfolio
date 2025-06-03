#include <stdio.h>
int checkSet(int *input, int input_length){
	if (input_length==0)
		return 0;
	else 
		return 1;
}
int findIntersection(int *input1, int input1_length, int *input2, int input2_length){
	int similar_elements=0;
	for (int i=0; i<input1_length; i++){
		if (i>0){
			input1-=input1_length-1;
			input2-=input2_length-1;
		}
		for (int j=0; j<input2_length; j++){
			input1+=i;
			input2+=j;
			if (*input1==*input2)
				similar_elements++;
		}
	}
	return similar_elements;
}
int findUnion(int *input1, int input1_length, int *input2, int input2_length){
	int different_elements=0;
	for (int i=0; i<input1_length; i++){
		different_elements++;
		if (i>0){
			input1-=input1_length-1;
			input2-=input2_length-1;
		}
		for (int j=0; j<input2_length; j++){
			input1+=i;
			input2+=j;
			if (*input1!=*input2)
				different_elements++;
		}
	}
	return different_elements;
}
float calculateJaccard(int *input1, int input1_length, int *input2, int input2_length){
	return findIntersection(input1, input1_length, input2, input2_length)/findUnion(input1, input1_length, input2, input2_length);
}
