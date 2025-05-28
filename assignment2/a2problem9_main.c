#include <stdio.h>
#include "a2problem9.h"
int main(void){
	int base;
        int expt;
        scanf("%d", &base);
        scanf("%d", &expt);
        int p=power(base,expt);
	printf("%d", p);
}
