#include <stdio.h>
#include <stdlib.h>
int* createArrayList(int initialCapacity, int *capacity, int *size){
	int *arr=(int *)malloc(sizeof(int)*initialCapacity);
	*size=0;
	*capacity=initialCapacity;
	return arr;
}
int* resizeArrayList(int *list, int *capacity, int resizeFactor){
	int *newlist=(int *)malloc((*capacity+resizeFactor)*sizeof(int));
	for (int i=0; i<*capacity; i++){
		newlist[i]=list[i];
	}
	*capacity=*capacity+resizeFactor;
	return newlist;
}
int* addItem(int *list, int *size, int *capacity, int value){
	if (*size>*capacity){
		list=resizeArrayList(list, capacity, *size-*capacity);
		*capacity=*size;
	}
	list[*size]=value;
	(*size)+=1;
	return list;
}
void removeItem(int *list, int *size, int index){
	list[index]-=list[index];
	for (; index<(*size)-1; index++){
		list[index]=list[index+1];
	}
	(*size)-=1;
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
