#include <stdio.h>
#include <stdlib.h>
int* createArrayList(int initialCapacity, int *capacity, int *size){
	int *arr=(int *)malloc(sizeof(int)*initialCapacity);
	*size=0;
	*capacity=initialCapacity;
	return arr;
}
int* resizeArrayList(int *list, int *capacity, int resizeFactor){
	int *newlist=(int *)malloc(*capacity*resizeFactor);
	for (int i=0; i<*capacity+resizeFactor; i++){
		newlist[i]=list[i];
	}
	return newlist;
}
int* addItem(int *list, int *size, int *capacity, int value){
	if (*size>*capacity){
		int *resized=resizeArrayList(list, capacity, *size-*capacity);
		*capacity==*size;
		resized[*size]=value;
		return resized;
	}
	list[*size]=value;
	return list;
}
void removeItem(int *list, int *size, int index){
	list[index]-=list[index];
	for (; index<(*size)-1; index++){
		list[index]=list[index+1];
	}
}
void printArrayList(int *list, int size){
	printf("[");
	for (int i=0; i<size; i++){
		if (i==size-1)
			printf("%d]", list[i]);
		else 
			printf("%d, ", list[i]);
	}
}
