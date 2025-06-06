#include <stdio.h>
int checkSet(int *input, int input_length){ // not sure what this is used for as I did not call it in future functions, but simply checking if the input length is 0 should give the desired output
	if (input_length==0)
		return 0;
	else 
		return 1;
}
int findIntersection(int *input1, int input1_length, int *input2, int input2_length){
	int similar_elements=0; 
	int *temp1=input1; // set temp values to revert pointer to original value after each iteration
	int *temp2=input2;
	for (int i=0; i<input1_length; i++){
		for (int j=0; j<input2_length; j++){
			input1=temp1;
			input2=temp2;
			input1+=i; // add pointer by the iterated value to loop through all pointer combinations
			input2+=j;
			if (*input1==*input2) // if the value each pointer points to equal each other they must be the same 
				similar_elements++;
		}
	}
	return similar_elements;
}
int findUnion(int *input1, int input1_length, int *input2, int input2_length){
	int *temp1=input1;
	int *temp2=input2;
	int a_length=input1_length+input2_length; // done for simplicity
	int a[a_length]; // create a temporary array to store all the values of 1 and 2 to better compare repeating values
	int in_a;
	int unique_count=0;
	for (int i=0; i<input1_length; i++){ // loop through all values and add it to a[]
		input1=temp1;
		input1+=i;
		a[i]=*input1;
	}
	for (int j=input1_length; j<input1_length+input2_length; j++){ // loop through all values and add it to a[], starting at the last element of input 1 so the new array doesn't accidentally rewrite any elements
		input2=temp2;
		input2+=j-input1_length;
		a[j]=*input2;
	}
	for (int k=0; k<a_length; k++){ 
		in_a=0; // assume the element is unique
		for (int p=0; p<k; p++){ // loop through all values before the current one, ensuring the first element gets added correctly and the next element will not be recorded
			if (a[k]==a[p]){
				in_a=1; 
				break; // break early to avoid any issues
			}
		}
		if (in_a==0)
			unique_count++;
	}
	return unique_count;
}
float calculateJaccard(int *input1, int input1_length, int *input2, int input2_length){
	float Intersect=findIntersection(input1, input1_length, input2, input2_length); // set the intersect and union values to float so the division will work correctly
	float Union=findUnion(input1, input1_length, input2, input2_length);
	float Jaccard=Intersect/Union;
	return Jaccard;
}
