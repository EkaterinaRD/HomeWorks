#include "list.h"

void create_cycle(LIST *head, LIST **end, char *data){

    if (search_list(head, data) == NULL){
        printf("We didn't find '%s'\n", data);
        return;
    }

    LIST *n = head;

    if (head == NULL){
        printf("Error!!\n");
        return;
    }

    while (!strcmp(n->value, data))
        n = n->next;

    (*end)->next = n;

    c = 1;

}

void test_list (LIST *head){

    LIST *t1, *t2;
    t1 = head;
    t2 = head;

    if(t2->next == NULL){
        printf("No cycle\n");
        return;
    }

//t1 идёт с шагом 1, t2 - с шагом 2
    do {
        t1 = t1->next;
        t2 = t2->next;

        if (t2->next == NULL){
            printf("No cycle\n");
            return;
        }

        t2 = t2->next;

        if (t2->next == NULL){
            printf("No cycle\n");
            return;
        }
    }while (t1 != t2 && t2->next != NULL);

    printf("List is cycle\n");
    c = 1;

}
