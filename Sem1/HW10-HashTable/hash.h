#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

typedef struct LIST {
  char *key;
  int value;
  struct LIST *next;
}LIST;

typedef struct HashTable {
  LIST *head;
  int length;
}HashTable;

HashTable *create_table(unsigned int table_size);
void add_table(HashTable *ht, char *key, unsigned int table_size);
void display_table(HashTable *ht, unsigned int table_size);
void clear_table(HashTable *ht, unsigned int table_size);
void get_value(HashTable *ht, char *key, unsigned int table_size);

void count_words(FILE *fp, unsigned int table_size, HashTable *ht);
void statictic(HashTable *ht, unsigned int table_size);

LIST *add_list(char *key);
unsigned int hash_FAQ6(const char *key, unsigned int table_size);
