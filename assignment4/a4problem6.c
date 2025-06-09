#include <stdio.h>
#include <stdlib.h>
int* createArrayList(int initialCapacity, int *capacity, int *size){
	*capacity=(int *)malloc(sizeof(int)*initialCapacity);
	*size=0;
	free(capacity);
	return capacity;
}
int* resizeArrayList(int *list, int *capacity, int resizeFactor){
	list=(int *)malloc(sizeof(capacity)+resizeFactor);
	free(list);
	return list;
}
int* addItem(int *list, int *size, int *capacity, int value){
	int counter=0;
	while (list[counter]!=0 && list[counter]!='\0'){
		counter++;
	}
	if (list[counter]==0)
		list[counter]=value;
	else if (list[counter]=='\0'){
		resizeArrayList(list, capacity, 1);
		list[counter]=value;
		list[counter+1]='\0';
	}
	return list;
}
void removeItem(int *list, int *size, int index){
	list[index]-=list[index];
	for (int i=0; i<index; i++){
		list[i+1]=list[i];
	}
}
void printArrayList(int *list, int size){
	printf("[");
	for (int i=0; i<size; i++){
		printf("%d, ", list[i]);
		if (i==size-1)
			printf("%d]", list[i]);
	}
}
