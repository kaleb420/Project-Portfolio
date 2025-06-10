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
	list=(int *)malloc(sizeof(capacity)+resizeFactor);
	free(list);
	return list;
}
int* addItem(int *list, int *size, int *capacity, int value){
	int counter=0;
	while (list[counter]!=*size && list[counter]!='\0'){
		if (list[counter]==0)
			list[counter]=value;
		else if (list[counter]=='\0'){
			resizeArrayList(list, capacity, 1);
			list[counter]=value;
			list[counter+1]='\0';
		}
		counter++;
	}
	return list;
}
void removeItem(int *list, int *size, int index){
	list[index]-=list[index];
	for (; index<size; index++){
		list[index]=list[index+1];
	}
	size-=1;
}
void printArrayList(int *list, int size){
	printf("[");
	for (int i=0; i<size; i++){
		printf("%d, ", list[i]);
		if (i==size-1)
			printf("%d]", list[i]);
	}
}
