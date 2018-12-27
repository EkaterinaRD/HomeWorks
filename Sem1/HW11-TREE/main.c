#include "tree.h"

int main() {

  TREE *head = NULL;
  int k, n = 11;
  int arr[n];

  fill_rand(arr, n);

//no_balance
  access(&head, arr[0]);

  for (int i = 1; i < n; i++) {
    add_number(&head, arr[i]);
  }

  FILE *fp;
  fp = fopen ("no_balance.dot","w");
  if (fp == NULL)
  {
    printf("Error\n");
  }

  fprintf(fp, "digraph G{\n");
  display_tree(head, fp);
  fprintf(fp, "}\n");

  fclose (fp);
  free_tree(&head);

//balance
  BubbleSort(arr, n);
  int c = n / 2;
  access(&head, arr[c]);
  balance_tree(&head, arr, 0, c - 1, c / 2);
  balance_tree(&head, arr, c + 1, n - 1, c + c / 2 + 1);

//  FILE *fp;
  fp = fopen ("balance.dot","w");
  if (fp == NULL)
  {
    printf("Error\n");
  }

  fprintf(fp, "digraph G{\n");
  display_tree(head, fp);
  fprintf(fp, "}\n");

  fclose (fp);
  free_tree(&head);

  return 0;
}
