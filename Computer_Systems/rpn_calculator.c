#include <stdio.h>
#include <stdlib.h>
int main(int argc, char **stk){
	long int arr_c=0;
	long int *arr=(long int *)malloc(argc*sizeof(long int));
	char *endptr;
	for (int i=0; i<argc; i++){
		arr[i]=0;
	}
	for (int j=1; j<argc; j++){
		if (stk[j][0]>=48 && stk[j][0]<=57){
			arr[arr_c]=strtol(stk[j], &endptr, 10);
			arr_c++;
		}
		else{
			if (stk[j][0]=='a'){
				arr[arr_c-2]=arr[arr_c-2]+arr[arr_c-1];
				arr[arr_c-1]=0;
			}
			else if (stk[j][0]=='s'){
				arr[arr_c-2]=arr[arr_c-2]-arr[arr_c-1];
				arr[arr_c-1]=0;
			}
			else if (stk[j][0]=='m'){
				arr[arr_c-2]=arr[arr_c-2]*arr[arr_c-1];
				arr[arr_c-1]=0;
			}
			else if (stk[j][0]=='d'){
				arr[arr_c-2]=arr[arr_c-2]/arr[arr_c-1];
               		        arr[arr_c-1]=0;
			}
			arr_c-=1;
		}
	}
	printf("%ld", arr[arr_c-1]);
	free(arr);
}
