#include <stdio.h>
void flatten_jagged_array(int *jagged_arr[], int jagged_arr_lens[], int num_jagged_arrs, int res_arr[]){
	int counter=0;
	for (int i=0; i<num_jagged_arrs; i++){
		for (int j=0; j<jagged_arr_lens[i]; j++){
			res_arr[counter]=jagged_arr[i][j];
			counter++;
		}
	}
	for (int k=0; k<counter; k++){
		printf("%d\n", res_arr[k]);
	}
}
