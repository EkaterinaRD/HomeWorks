#include "sort.h"

void fillRand(int *a, long int n){
    int i;
    for (i = 0; i < n; i++)
       a[i] = rand() % N;
}

/*void Display(int *a, int n){
    int i;
    for (i = 0; i < n; i++)
       printf("%d ", a[i]);
    printf("\n");
}*/

void swap(int *x, int *y){
    int tmp = *x;
    *x = *y;
    *y = tmp;
}
