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
	list=createArrayList(initialCapacity, capacity, size);
	addItem(list, size, capacity, value);
	removeItem(list, size, index);
	list=resizeArrayList(list, capacity, resizeFactor); 
	printArrayList(list, *size);
	free(capacity);
	free(size);
	free(list);
}
