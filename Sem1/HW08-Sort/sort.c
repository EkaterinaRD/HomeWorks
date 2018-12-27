#include "sort.h"

//O(n^2)
void BubbleSort(int *a, long int n){
    
    int i, j;
    for (i = 0; i < n - 1; i++){
        for (j = 0; j < n - 1 - i; j++){
            if (a[j] > a[j + 1])
            swap(&a[j], &a[j + 1]);
        }
    }
}

//O(nlogn)
void QuickSort(int *a, int left, int right){

    int p = a[left], l_hold = left, r_hold = right;
    while(left < right){
        while ((a[right] >= p) && (left < right))
            right--;
        if (left != right){
            a[left] = a[right];
            left++;
        }     
        while ((a[left] <= p) && (left < right))
            left++;
        if (left != right){
            a[right] = a[left];
            right--;
        }    
    }
    a[left] = p;
    p = left;
    left = l_hold;
    right = r_hold;
    if (left < p)
        QuickSort(a, left, p - 1);
    if (right > p)
        QuickSort(a, p + 1, right);  

}


//O(n)
void CountSort(int *a, long int n){

    int i, max, j, b = 0;
    int *c;

    max =  a[0];
    for (i = 1; i < n; i++){
        if (a[i] > max)
            max = a[i];
    }   

    c = (int *)calloc((max + 1), sizeof(int));

    for (i = 0; i < n; i++)
        c[a[i]]++;

    for (i = 0; i <= max; i++){
        for (j = 0; j < c[i]; j++){
            a[b] = i;
            b++;
        }
    }    

    free(c);

}
