#include <stdio.h>
#include "a3problem5.h"
int main(void){
	int arr1[20];
	int arr2[20];
	int arr3[20];
	int arr4[20];
	int jagged_arr[]={arr1[20],arr2[20],arr3[20],arr4[20]};
	int jagged_arr_lens[20];
	int num_jagged_arrs[20];
	int res_arr[20];
	flatten_jagged_array(jagged_arr, jagged_arr_lens, num_jagged_arrs, res_arr);
	return 0;
}
