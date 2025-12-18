#include <stdio.h>
#include "a2problem1.h"
int main(void){
        int N, c;
        scanf("%d", &N);
        scanf("%d", &c);
        printf("%f", probabilityOfPassingMCQTest(N, c));
}
