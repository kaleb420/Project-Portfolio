#include <stdio.h>
#include <stdlib.h>
#include "a4problem6.h"
int main(void){
	int initialCapacity;
	int *capacity=malloc(sizeof(int));
	int *size=malloc(sizeof(int));
	int *list=malloc(initialCapacity*sizeof(int));
	int value;
	int index;
	int resizeFactor;
	int size2=*size;
	createArrayList(initialCapacity, capacity, size);
	addItem(list, size, capacity, value);
	removeItem(list, size, index);
	resizeArrayList(list, capacity, resizeFactor);
	printArrayList(list, size2);
	free(capacity);
	free(size);
	free(list);
}
