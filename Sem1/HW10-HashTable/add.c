#include "hash.h"

unsigned int hash_FAQ6(const char *key,unsigned  int table_size) {

  unsigned int h = 0;

  for (; *key; key++) {
    h += (unsigned char)(*key);
    h += (h << 10);
    h ^= (h >> 6);
  }

  h += (h << 3);
  h ^= (h >> 11);
  h += (h << 15);

  return h % table_size;
}

LIST *add_list(char *key) {

  LIST *list = malloc(sizeof(LIST));
  if (!list) {
    printf("Error!\n");
    return NULL;
  }

  list->value = 1;
  int len = strlen(key) + 1;
  char *tmp = malloc(len);
  if (!tmp) {
    printf("Error!\n");
    return NULL;
  }

  //tmp[sizeof(key)] = "\0";
  strcpy(tmp, key);
  list->key = tmp;

  return list;
}
