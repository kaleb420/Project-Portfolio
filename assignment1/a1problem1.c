#include <stdio.h>
int main(void){
	char sleeping;
	char mom;
	char morning;
	int phone;
	scanf("%c", &morning);
	scanf("%c", &mom);
	scanf("%c", &sleeping);
	if(sleeping=='y'){
		phone=0;
	}
	else if(sleeping=='n'){
		if(mom=='y' && morning=='y'){
			phone=1;
		}
		else if(mom=='y' && morning=='n'){
			phone=1;
		}
		else if(mom=='n' && morning=='y'){
			phone=0;
		}
		else if(mom=='n' && morning=='n'){
			phone=1;
		}
	}
	if(phone==0){
		printf("don't answer phone\n");
	}
	else if(phone==1){
		printf("answer phone\n");
	}
}
