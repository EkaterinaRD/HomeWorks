#include <stdio.h>
#include <stdlib.h>
#include <time.h>

typedef struct TREE {

  int key;
  struct TREE *left;
  struct TREE *right;

}TREE;

TREE **access(TREE **T, int x);
void add_number(TREE **T, int x);
void balance_tree (TREE **T, int *arr, int a, int b, int n);

void free_tree(TREE **T);
void display_tree(TREE *T, FILE *fp);
void fill_rand(int *a, int n);
void swap(int *x, int *y);
void BubbleSort(int *a, int n);
