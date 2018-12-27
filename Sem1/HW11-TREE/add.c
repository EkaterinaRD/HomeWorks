#include "tree.h"

void free_tree(TREE **T) {

  if (*T == NULL) {
    return;
  }

  free_tree(&(*T)->left);
  free_tree(&(*T)->right);

  free(*T);
  *T = NULL;
}

void display_tree(TREE *T, FILE *fp) {

  if (T == NULL) {
    fprintf(fp, "\n");
    return;
  }

  if (T->left != NULL) {
    fprintf(fp, "%d", T->key);
    fprintf(fp, "->%d;\n", T->left->key);
    display_tree(T->left, fp);
  }
  if (T->right != NULL) {
    fprintf(fp, "%d", T->key);
    fprintf(fp, "->%d;\n", T->right->key);
    display_tree(T->right, fp);
  }
  /*if (T != NULL && T->left == NULL && T->left == NULL) {

  }*/
}

void fill_rand(int *a, int n) {

  srand(time(NULL));
  for (int i = 0; i < n; i++) {
    a[i] = rand() % 231;
  }

}

void swap(int *x, int *y) {
    int tmp = *x;
    *x = *y;
    *y = tmp;
}

void BubbleSort(int *a, int n) {

    int i, j;
    for (i = 0; i < n - 1; i++){
        for (j = 0; j < n - 1 - i; j++){
            if (a[j] > a[j + 1])
            swap(&a[j], &a[j + 1]);
        }
    }
}
