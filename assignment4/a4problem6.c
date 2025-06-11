#include <stdio.h>
#include <stdlib.h>
int* createArrayList(int initialCapacity, int *capacity, int *size){
	int *arr=(int *)malloc(sizeof(int)*initialCapacity);
	size=0;
	capacity=initialCapacity;
	return arr;
}
int* resizeArrayList(int *list, int *capacity, int resizeFactor){
	int *newlist=(int *)malloc(sizeof(capacity)+resizeFactor);
	int counter=0;
	while (list[counter]!='\0'){
		newlist[counter]=list[counter];
		counter++;
	}
	return newlist;
}
int* addItem(int *list, int *size, int *capacity, int value){
	if ((*size)>(*capacity)){
		resizeArrayList(list, capacity, *size-*capacity);
	}
	list[*size]=value;
	return list;
}
void removeItem(int *list, int *size, int index){
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
