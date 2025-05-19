#include <stdio.h>
int main(void){
	char sleeping;
	char mom;
	char morning;
	int phone;
	if(sleeping=='y'){
		phone=0;
	}
	else if(sleeping=='n'){
		if(mom=='y'){
			phone=1;
		}
		else if(morning=='n'){
			phone=1;
		}
		else{
			phone=0;
		}
	}
	if(phone==0){
		printf("don't answer phone\n");
	}
	else if(phone==1){
		printf("answer phone\n");
	}
}
