#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define N 32767

/*unsigned int start_time;
unsigned int end_time;
unsigned int t;*/

void fillRand(int *a, long int n);
//void Display(int *a, int n);
void swap(int *x, int *y);

void BubbleSort(int *a, long int n);
void QuickSort(int *a, int left, int right);
void CountSort(int *a, long int n);

/*
    end_time = clock();
    t = end_time - start_time;
    printf("%5u mls.", t);*/