#include <stdio.h>
#include <stdlib.h>
long **read_content(void){
	long **arr=(long **)malloc(sizeof(long *)*2);
	long *index0;
	long *index1=(long *)malloc(sizeof(long)*4);
	long number=0;
	long *integers=(long *)malloc(sizeof(long)*20);
	long sum=0;
	long average=0;
	long range=0;
	arr[0]=index0;
	arr[1]=index1;
	index1[0]=integers;
	index1[1]=sum;
	index1[2]=average;
	index1[3]=range;
	int smallest=10000000;
	int largest=-10000000;
	while (index0[number]!='\0'){
		sum+=index0[number];
		if (index0[number]>largest)
			largest=index0[number];
		if (index0[number]<smallest)
			smallest=index0[number];
		number+=1;
	}
	average=sum/(number+1);
	range=largest-smallest;
	printf("not freed");
	fflush(stdout);
	free(index1);
	free(integers);
	return arr;
}
int main(void){
	long **arr=read_content();
	printf("The array contains: [%ld]\n", arr[0][0]);
	printf("There are %ld numbers,\n", arr[1][0]);
	printf("The sum is %ld,\n", arr[1][1]);
	printf("The average is %ld,\n", arr[1][2]);
	printf("The range is %ld.", arr[1][3]);
	free(arr);
}
