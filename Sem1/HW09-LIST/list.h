#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct LIST{

    char value[10];
    struct LIST *next;

}LIST;

int num, c;

void push_front(LIST **head, LIST **end, char *data);
void push_back(LIST **head, LIST **end, char *data);
void insert_after(LIST **head, LIST **end, char *data);
void delete_element(LIST **head, LIST **end, char *data);
void delete_list(LIST **head, LIST **end);
void reverse(LIST **head, LIST **end);

void create_cycle(LIST *head, LIST **end, char *data);
void test_list (LIST *head);

void display(LIST *head);
LIST *search_list(LIST *head, char *data);
LIST *predecessor_list(LIST *head, char *data);
