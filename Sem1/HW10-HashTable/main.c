#include "hash.h"

int main() {

  unsigned int TABLE_SIZE;
  printf("Input size table: ");
  scanf("%u", &TABLE_SIZE);

  clock_t start = clock();

  HashTable *ht = create_table(TABLE_SIZE);

  FILE *fp;
  fp = fopen ("book.txt","r");
  if (fp == NULL)
  {
    printf("Error\n");
    clear_table(ht, TABLE_SIZE);
    return 0;
  }

  count_words(fp, TABLE_SIZE, ht);
//  display_table(ht, TABLE_SIZE);
  statictic(ht, TABLE_SIZE);

  clear_table(ht, TABLE_SIZE);

  fclose (fp);

  clock_t finish = clock();
  printf("Time work: %lf\n", (double)(finish - start) / CLOCKS_PER_SEC);
  return 0;
}
