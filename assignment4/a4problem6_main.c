#include <stdio.h>
#include "a4problem6.h"
int main(void){
	int initialCapacity;
	int *capacity;
	int *size;
	int *list;
	int value;
	int index;
	int resizeFactor;
	createArrayList(initialCapacity, capacity, size);
	addItem(list, size, capacity, value);
	removeItem(list, size, index);
	resizeArrayList(list, capacity, resizeFactor);
	printArrayList(list, *size);
}
