#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>
long **read_content(void){
	long **arr=(long **)malloc(sizeof(long *)*2);
	long *index0=(long *)malloc(sizeof(long *)*20);
	long *index1=(long *)malloc(sizeof(long *)*4); // allocate memory as requested on the pdf
	arr[0]=index0;
	arr[1]=index1;
	index1[0]=0;
	index1[1]=0;
	index1[2]=0;
	index1[3]=0;
	int smallest=10000000;
	int largest=-10000000;
	int size=0;
	long temp;
	while (1==1){ // create an infinite loop that only breaks if the input is not an integer 
		if (scanf("%ld", &temp)==1){
			index0[size]=temp; // set the last value in index0 to the value inputted, and increment the counter
			size++;
		}
		else{
			break;
		}
	}
	for (int i=0; i<size; i++){ // basic arithmetic to calculate statistics
		index1[1]+=index0[i];
		if (index0[i]>largest)
			largest=index0[i];
		if (index0[i]<smallest)
			smallest=index0[i];
	}
	index1[0]=size; // each index corresponds to an appropriate statistic
	index1[2]=index1[1]/size;
	index1[3]=largest-smallest;
	printf("The array contains: ");
        for (int i=0; i<size; i++){ // create a for loop here because otherwise the size would be lost and could not be used in the main function, so its easier to print in this function
                if (i==size-1)
                        printf("%ld],\n", arr[0][i]);
                else if (i==0)
                        printf("[%ld, ", arr[0][i]);
                else
                        printf("%ld, ", arr[0][i]);
        }
	return arr;
}
int main(void){
	long **arr=read_content();
	printf("There are %ld numbers,\n", arr[1][0]); // print each variable 
	printf("The sum is %ld,\n", arr[1][1]);
	printf("The average is %ld,\n", arr[1][2]);
	printf("The range is %ld.", arr[1][3]);
	free(arr);
}
