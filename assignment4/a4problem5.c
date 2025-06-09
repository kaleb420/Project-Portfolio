#include <stdio.h>
int compareAsc(int a, int b){
	if (a>b)
		return 1;
	else
		return 0;
}
int compareDsc(int a, int b){
	if (a<b)
		return 1;
	else
		return 0;
}
void sort(int arr[], int size, int(*compare)(int, int)){
	int temp;
	for (int i=0; i<size; i++){
		int maxindex=-10000;
		int minindex=10000;
		for (int j=0; j<size; j++){
			if (*compare==compareAsc){
				if (compareAsc(j,maxindex)==1)
					maxindex=j;
			}
			else if (*compare==compareDsc){
				if (compareDsc(j,minindex)==1)
					minindex=j;
			}
		}
		if (*compare==compareAsc){
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
