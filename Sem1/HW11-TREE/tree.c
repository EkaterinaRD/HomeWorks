#include "tree.h"

TREE **access(TREE **T, int x) {

  while ((*T) != NULL) {
    if ((*T)->key == x) {
      return T;
    } else if ((*T)->key > x) {
      T = &(*T)->left;
    } else {
      T = &(*T)->right;
    }
  }

  TREE *newEl = malloc(sizeof(TREE));
  if (newEl == NULL) {
    printf("Error\n");
    return 0;
  }

  newEl->key = x;
  newEl->left = NULL;
  newEl->right = NULL;
  *T = newEl;

  return T;
}

void add_number(TREE **T, int x) {

  while (*T != NULL) {
    if ((*T)->key > x) {
      T = &(*T)->left;
    } else if ((*T)->key < x) {
      T = &(*T)->right;
    } else {
      return;
    }
  }

  TREE *newEl = malloc(sizeof(TREE));
  if (newEl == NULL) {
    printf("Error\n");
    return;
  }

  newEl->key = x;
  newEl->left = NULL;
  newEl->right = NULL;
  *T = newEl;
}

void balance_tree (TREE **T, int *arr, int a, int b, int n) {

  int a1 = n + 1, b1 = n - 1;

  add_number(T, arr[n]);

  if ((b1 - a) == 0) {
    add_number(T, arr[n - 1]);
  //  return;
  } else if ((b1 - a) == 1) {
    add_number(T, arr[n - 1]);
    add_number(T, arr[n - 2]);
  //  return;
  } else if ((b1 - a) > 2) {
    balance_tree(T, arr, a, b1, n - n / 2);
  }

  if ((b - a1) == 0) {
    add_number(T, arr[n + 1]);
  //  return;
  } else if ((b - a1) == 1) {
    add_number(T, arr[n + 1]);
    add_number(T, arr[n + 2]);
  //  return;
  } else if ((b - a1) > 2 ) {
    balance_tree(T, arr, a1, b, n + n / 2);
  }
}
