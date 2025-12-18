#include <stdio.h>
void flatten_jagged_array(int *jagged_arr[], int jagged_arr_lens[], int num_jagged_arrs, int res_arr[]){ // the values were initialized based off indirect asumptions from the pdf file
	int counter=0;
	for (int i=0; i<num_jagged_arrs; i++){ // loop through the number of arrays 
		for (int j=0; j<jagged_arr_lens[i]; j++){ // loop through the array
			res_arr[counter]=jagged_arr[i][j]; // this was done accidentally through testing possibilities, I knew my error lied somewhere in this line, and I'd have to set res_arr to the counter to fill the appropriate element, and based on the intialization I'd have to call one array to a specific spot, then I called the next, I can't fully explain this in c terms but I believe what is happening is similar to a python function to select a list within a list, then selecting an appropriate value
			counter++;
		}
	}
	for (int k=0; k<counter; k++){
		printf("%d\n", res_arr[k]); // loop through all values to print them 
	}
}
