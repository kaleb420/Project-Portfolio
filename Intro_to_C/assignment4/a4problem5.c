#include <stdio.h>
int compareAsc(int a, int b){
	if (a>b)
		return 1;
	else
		return 0;
}
int compareDsc(int a, int b){ // these were both described in the pdf
	if (a<b)
		return 1;
	else
		return 0;
}
void sort(int arr[], int size, int(*compare)(int, int)){
	int temp;
	for (int i=0; i<size; i++){ // iterate through each value in the array
		int maxindex=-10000;
		int minindex=10000;
		for (int j=0; j<size; j++){
			if (*compare==compareAsc){ // call the appropriate function depending if the user wants ascending or descending order
				if (compareAsc(j,maxindex)==1) // if it's the max index at the time, j is the new max index
					maxindex=j;
			}
			else if (*compare==compareDsc){ // same as last but with min index
				if (compareDsc(j,minindex)==1)
					minindex=j;
			}
		}
		if (*compare==compareAsc){ // set a temp value, arr[i] equals the max value, and max/min index is set to the temp value 
			temp=arr[i];
			arr[i]=arr[maxindex];
			arr[maxindex]=temp;
		}
		else if (*compare==compareDsc){
			temp=arr[i];
			arr[i]=arr[minindex];
			arr[minindex]=temp;
		}
	}
	printf("%ls", arr);
}
