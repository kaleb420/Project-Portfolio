#include <stdio.h>
#include "a2problem4.h"
int main(void){
        long n;
        long fact;
        scanf("%ld", &n);
        fact=subfactorial(n);
        printf("%ld", fact);
}
