#include <stdio.h>
#include <stdlib.h>
int* createArrayList(int initialCapacity, int *capacity, int *size){
	int *arr=(int *)malloc(sizeof(int)*initialCapacity); // allocate memory and define variables as the pdf says 
	*size=0;
	*capacity=initialCapacity;
	return arr;
}
int* resizeArrayList(int *list, int *capacity, int resizeFactor){
	int *newlist=(int *)malloc((*capacity+resizeFactor)*sizeof(int));
	for (int i=0; i<*capacity; i++){ // create a new bigger list and copy the variables over
		newlist[i]=list[i];
	}
	*capacity=*capacity+resizeFactor; // update the capacity of the list 
	return newlist;
}
int* addItem(int *list, int *size, int *capacity, int value){
	if (*size>*capacity){ // if the size required is over the capacity, increase the list capacity
		list=resizeArrayList(list, capacity, *size-*capacity); 
		*capacity=*size; // capacity is equal to size because the list was resized
	}
	list[*size]=value; // add the new value 
	(*size)+=1;
	return list;
}
void removeItem(int *list, int *size, int index){
	list[index]-=list[index]; // set the value to 0
	for (; index<(*size)-1; index++){ // move all the items greater than index up 
		list[index]=list[index+1];
	}
	(*size)-=1; // reduce the size of the list by 1
}
void printArrayList(int *list, int size){
	printf("["); // I don't know why this doesn't work but it probably has to do with how the variables are intialized and changed throughout the program, I ran a test case and it worked fine 
	for (int i=0; i<size; i++){
		if (i==size-1)
			printf("%d]", list[i]);
		else 
			printf("%d, ", list[i]);
	}
}
