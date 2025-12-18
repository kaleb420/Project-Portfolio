#include <stdio.h>
#include "a3problem5.h"
int main(void){
	/*int arr1[10];
	int arr2[10];
	int arr3[10];
	int arr4[10];
	int *jagged_arr[]={arr1,arr2,arr3,arr4};
	int jagged_arr_lens[]={sizeof(arr1)/4,sizeof(arr2)/4,sizeof(arr3)/4,sizeof(arr4)/4};
	int num_jagged_arrs=sizeof(jagged_arr_lens);
	printf("This is the number of jagged arrs: %ld", sizeof(jagged_arr_lens));
	*/ 
	int arr1[]={4,3,1}; // before this I originally tried to have a generalizable set but struggled to ensure the pointers would remain in bounds of the array, so instead I submitted this specific example to see if it would work for all cases and it did
	int arr2[]={2,3};
	int arr3[]={88,9,31,23};
	int arr4[]={100};
	int *jagged_arr[]={arr1,arr2,arr3,arr4};
	int jagged_arr_lens[]={3,2,4,1};
	int num_jagged_arrs=4;
	int res_arr[10];
	flatten_jagged_array(jagged_arr, jagged_arr_lens, num_jagged_arrs, res_arr);
	return 0;
}
