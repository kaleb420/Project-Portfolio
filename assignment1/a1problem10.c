#include <stdio.h>
int main(void){
	int odigit;
	int tdigit;
	int thdigit;
	int fdigit;
	int encrypted;
	int decrypted;
	char type;
	scanf("%1d%1d%1d%1d", &odigit, &tdigit, &thdigit, &fdigit);
		encrypted=(((odigit+7)%10)*10)+((tdigit+7)%10)+(((thdigit+7)%10)*1000)+(((fdigit+7)%10)*100);
		decrypted=(((odigit+13)%10)*10)+((tdigit+13)%10)+(((thdigit+13)%10)*1000)+(((fdigit+13)%10)*100);
	scanf(" %c", &type);
	if (type=='e'){
		if (encrypted>1000){
			printf("Encrypted number: %d", encrypted);
		}
		else if(encrypted<1000){
			printf("Encrypted number: 0%d", encrypted);
		}
		else if(encrypted<100){
			printf("Encrypted number: 00%d", encrypted);
		}
		else if(encrypted<10){
			printf("Encrypted number: 000%d", encrypted);
		}
	}
	else if(type=='d'){
		if (encrypted>1000){
			printf("Decrypted number: %d", decrypted);
		}
		else if(encrypted<1000){
			printf("Decrypted number: 0%d", decrypted);
		}
		else if(encrypted<100){
			printf("Decrypted number: 00%d", decrypted);
		}
		else if(encrypted<10){
			printf("Decrypted number: 000%d", decrypted);
		}
	}
}
