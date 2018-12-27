#include "hash.h"

HashTable *create_table(unsigned int table_size) {

  HashTable *table = malloc(table_size * sizeof(HashTable));
  if (!table) {
    printf("Error!\n");
    return NULL;
  }

//  printf("Ura - %d\n", table_size);
  return table;
}

void add_table(HashTable *ht, char *key, unsigned int table_size) {

  unsigned int hash = hash_FAQ6(key, table_size);

  LIST *last = ht[hash].head;
  if (!ht[hash].head) {
    ht[hash].head = add_list(key);
    ht[hash].length++;
    return;
  } else {
    while (last->next && strcmp(last->key, key)) {
      last = last->next;
    }
  }

  if (!strcmp(last->key, key)) {
    last->value++;
  } else {
    last->next = add_list(key);
    ht[hash].length++;
  }

}

void display_table(HashTable *ht, unsigned int table_size) {

  for (int i = 0; i < table_size; i++) {
    if (ht[i].head) {
      LIST *last = ht[i].head;
      while (last) {
        printf("%s - %d\n", last->key, last->value);
        last = last->next;
      }
    }
  }

}

void clear_table(HashTable *ht, unsigned int table_size) {

  for (int i = 0; i < table_size; i++) {
    LIST *tmp = ht[i].head;
    int j = 0;
    while (ht[i].head) {
      ht[i].head = tmp->next;
      free(tmp->next);
      free(tmp);
      tmp = ht[i].head;
    }

    ht[i].length = 0;
    free(ht[i].head);
  }

  free(ht);

}

void get_value(HashTable *ht, char *key, unsigned int table_size) {

  unsigned int hash = hash_FAQ6(key, table_size);
  LIST *tmp = ht[hash].head;

  while (tmp) {
    if (!strcmp(tmp->key, key)) {
      printf("%s - %d\n", key, tmp->value);
      return;
    } else {
      tmp = tmp->next;
    }
  }

  printf("This element is not! - %s\n", key);
}
