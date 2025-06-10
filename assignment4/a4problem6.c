#include <stdio.h>
#include <stdlib.h>
int* createArrayList(int initialCapacity, int *capacity, int *size){
	int *arr=(int *)malloc(sizeof(int)*initialCapacity);
	size=0;
	capacity=initialCapacity;
	free(arr);
	return arr;
}
int* resizeArrayList(int *list, int *capacity, int resizeFactor){
	int *newlist=(int *)malloc(sizeof(capacity)+resizeFactor);
	int counter=0;
	while (list[counter]!='\0'){
		newlist[counter]=list[counter];
	}
	free(newlist);
	return newlist;
}
int* addItem(int *list, int *size, int *capacity, int value){
	if (*size>*capacity){
		resizeArrayList(list, capacity, *size-*capacity);
	}
	list[*size]=value;
	return list;
}
void removeItem(int *list, int *size, int index){
	list[index]=list[index]-list[index];
	for (; index<size; index++){
		list[index]=list[index+1];
	}
	size-=1;
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
