#include "hash.h"

void count_words(FILE *fp, unsigned int table_size, HashTable *ht) {
  char str[50];
  //int i = 0;
  //char c;
  while (fscanf(fp, "%s", str) == 1) {
    int len = strlen(str);
    char word[50];

    for (int i = 0; i < 50; i++) {
      word[i] = 0;
    }

    for (int i = 0; i < len; i++) {
      if (str[i] == '.' || str[i] == ',' || str[i] == '!' || str[i] == '?' || str[i] == ':'
          || str[i] == ';' || str[i] == '-' || str[i] == '(' || str[i] == ')' ||
          /*str[i] == '«' || str[i] == '»' ||*/ str[i] == '*') {
        continue;
      }

      char sym[2];
      sym[0] = str[i];
      sym[1] = '\0';
      strcat(word, sym);
    }

    if (strlen(word) != 0) {
      add_table(ht, word, table_size);
    }
  }
}

void statictic(HashTable *ht, unsigned int table_size) {

  int totalWords = 0;
  int totalUnicWords = 0;
  int emptyLists = 0;
  char *mostPopularWord = "";
  int mostPopularWordVal = 0;
  int minListsLength = ht[0].length;
  int maxListsLength = 0;

  for (int i = 0; i < table_size; i++) {
    int len = ht[i].length;

    if (!len) {
      emptyLists++;
    }

    totalUnicWords += len;

    if (maxListsLength < len) {
      maxListsLength = len;
    }

    if (minListsLength > len) {
      minListsLength = len;
    }
  }

  for (int i = 0; i < table_size; i++) {
    LIST *tmp = ht[i].head;

    while (tmp) {
      if (tmp->value > mostPopularWordVal) {
        mostPopularWordVal = tmp->value;
        mostPopularWord = tmp->key;
      }

      totalWords += tmp->value;
      tmp = tmp->next;
    }
  }

  FILE *fp;
  fp = fopen ("Result.txt","w");
  if (fp == NULL)
  {
    printf("Error\n");
    return;
  }
  fprintf(fp, "Size table: %u\n", table_size);
  fprintf(fp, "Total words: %d\n", totalWords);
  fprintf(fp, "Uncial words: %d\n", totalUnicWords);
  fprintf(fp, "Most popular word: %s\n", mostPopularWord);
  fprintf(fp, "Most popular word frequency: %f\n", (float) mostPopularWordVal / totalWords);
  fprintf(fp, "Longest list length: %d\n", maxListsLength);
  fprintf(fp, "Shortest list length: %d\n", minListsLength);
  fprintf(fp, "Average list length: %f\n", (float) totalUnicWords / table_size);
  fprintf(fp, "Percent of empty lists: %f\n", (float) 100 * emptyLists / table_size);

  fclose (fp);
}
