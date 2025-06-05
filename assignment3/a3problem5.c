#include <stdio.h>
void flatten_jagged_array(int jagged_arr, int jagged_arr_lens, int num_jagged_arrs, int res_arr){
	int *counter;
	for (int *i; *i<num_jagged_arrs; i++){
		for (int *j; *j<jagged_arr_lens[i]; j++){
			res_arr[counter]=jagged_arr[i][j];
			counter++;
		}
	}
	printf("%d", res_arr);
}
