#include <stdio.h>
#include "a2problem5.h"
int main(void){
        int n;
        scanf("%d", &n);
	collatz(n);
        collatzIterative(n);
}
