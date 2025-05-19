#include <stdio.h>
int main(void){
	int code;
	char digits[5];
	char encrypted[5];
	int i=0;
	printf("Enter a four digit integer: ");
	scanf("%4d", &code);
	sprintf(digits, "%d", code);
	printf("%s", encrypted);
}
