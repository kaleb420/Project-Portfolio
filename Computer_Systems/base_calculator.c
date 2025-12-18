#include <stdio.h>
#include <stdlib.h>
int length(char* str){
	int counter=0;
	while (str[counter]!='\0'){
		counter++;
	}
	return counter;
}
char* substring(char* number){
	char* temp=(char*)malloc((length(number)-2)*sizeof(char));
	for (int i=2; i<length(number); i++){
		temp[i-2]=number[i];
	}
	return temp;
}
int math_pow(int base, int power){
	int total=1;
	for (int i=0; i<power; i++){
		total*=base;
	}
	return total;
}
int binary(char* number){
	int size=length(number);
	int total=0;
	for (int i=0; i<size; i++){
		total+=(number[i]-48)*math_pow(2,size-i-1);
	}
	return total;
}
int hexadecimal(char* number){
	int size=length(number);
	int total=0;
	for (int i=0; i<size; i++){
		if (number[i]>=48 && number[i]<=57)
			total+=(number[i]-48)*math_pow(16,size-i-1);
		else
			total+=(number[i]-87)*math_pow(16,size-i-1);
	}
	return total;
}
int decimal(char* number){
	int size=length(number);
	int total=0;
	for (int i=0; i<size; i++){
		total+=(number[i]-48)*math_pow(10,size-i-1);
	}
	return total;
} 
int main(int argc, char **stk){
	long int arr_c=0;
	long int *arr=(long int *)malloc(argc*sizeof(long int));
	for (int i=0; i<argc; i++){
		arr[i]=0;
	}
	for (int j=1; j<argc; j++){
		if (stk[j][1]=='b'){
			arr[arr_c]=binary(substring(stk[j]));
			arr_c++;
		}
		else if (stk[j][1]=='x'){
			arr[arr_c]=hexadecimal(substring(stk[j]));
			arr_c++;
		}
		else if (stk[j][0]>=48 && stk[j][0]<=57){
			arr[arr_c]=decimal(stk[j]);
			arr_c++;
		}
		else{
			if (stk[j][0]=='a'){
                                arr[arr_c-2]=arr[arr_c-2]+arr[arr_c-1];
                                arr[arr_c-1]=0;
                        }
                        else if (stk[j][0]=='s'){
                                arr[arr_c-2]=arr[arr_c-2]-arr[arr_c-1];
                                arr[arr_c-1]=0;
                        }
                        else if (stk[j][0]=='m'){
                                arr[arr_c-2]=arr[arr_c-2]*arr[arr_c-1];
                                arr[arr_c-1]=0;
                        }
                        else if (stk[j][0]=='d'){
                                arr[arr_c-2]=arr[arr_c-2]/arr[arr_c-1];
                                arr[arr_c-1]=0;
                        }
                        arr_c-=1;
                }
	}
	printf("%ld", arr[arr_c-1]);
	free(arr);
} 
