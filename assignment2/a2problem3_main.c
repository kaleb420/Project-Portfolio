#include <stdio.h>
#include "a2problem3.h"
int main(void){
        int X, n, L, R;
        scanf("%d", &X);
        scanf("%d", &n);
        scanf("%d", &L);
        scanf("%d", &R);
        printf("%f", computeSeries(X,n,L,R));
}
