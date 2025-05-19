#include <stdio.h>
int main(void){
	char one;
	char two;
	one='a';
	two='a';
	while (one!='z'){
		if (two!='z'){
			printf("www.%c%c.com\n", one, two);
			two++;
		}
		else if (two=='z'){
			printf("www.%c%c.com\n", one, two);
			one++;
			two='a';
		}
	}
	while (two!='z'){
		printf("www.%c%c.com\n", one, two);
		two++;
	}
	printf("www.%c%c.com\n", one, two);
}	
	
