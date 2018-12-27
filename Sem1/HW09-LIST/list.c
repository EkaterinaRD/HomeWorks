#include "list.h"

void push_front(LIST **head, LIST **end, char *data) {

    LIST *p = malloc(sizeof(LIST));
    if (p == NULL)
        return;

    strcpy(p->value, data);
    p->next = (*head);

    if (num == 0) {
        (*head) = p;
        (*end) = p;
	      num++;
    }
    else {
        (*head) = p;
	      num++;
    }

}

void push_back(LIST **head, LIST **end, char *data) {

    LIST *p = malloc(sizeof(LIST));
    if (p == NULL)
        return;

    strcpy(p->value, data);

    if (num == 0) {
        (*head) = p;
        (*end) = p;
	      num++;
    }
    else {
        p->next = NULL;
        (*end)->next = p;
        (*end) = p;
        num++;
    }

}

void insert_after(LIST **head, LIST **end, char *data) {

    char input[10];
    printf("Input insert_str: ");
    scanf("%s", input);

    if (*head == NULL) {
        printf("List is NULL\n");
        return;
    }

    if (search_list(*head, input) == NULL) {
        printf("We didn't find '%s'\n", input);
        return;
    }

    LIST *p = malloc(sizeof(LIST));

    if (p == NULL) {
        printf("Error\n");
        return;
    }
    strcpy(p->value,data);

    LIST *cur = search_list(*head, input);

    if (cur != (*end)) {
        p->next = cur->next;
        cur->next = p;
        num++;
    }
    else {
        p->next = cur->next;
        cur->next = p;
        (*end) = p;
        num++;
    }

}

void delete_element(LIST **head, LIST **end, char *data) {

    if (*head == NULL) {
        printf("List is NULL\n");
        return;
    }

    if (search_list(*head, data) == NULL) {
        printf("We didn't find '%s'\n", data);
        return;
    }

    LIST *cur = search_list(*head, data);
    LIST *pred = predecessor_list(*head, data);

    if (pred == NULL) {
        (*head) = cur->next;
        num--;
    }
    else {
        pred->next = cur->next;
        if (pred->next == NULL)
        (*end) = pred;

        num--;
    }

    free(cur);

}

void delete_list(LIST **head, LIST **end) {

    if (*head == NULL) {
       printf("Emm... List is NULL.\n");
       return;
    }

    LIST *prev = NULL;

    while(*head != NULL) {
       prev = (*head);
       (*head) = (*head)->next;
       free(prev);
    }

    (*head) = NULL;
    (*end) = (*head);

    num = 0;
    printf("List is NULL.\n");

}

void reverse(LIST **head, LIST **end) {

    printf("Before: ");
    display(*head);

    LIST *cur = *head, *next = NULL, *prev = NULL;

    while (cur != NULL) {
        next = cur->next;
        cur->next = prev;
        prev = cur;
        cur = next;
    }

    (*end) = (*head);
    (*head) = prev;

    printf("After: ");
    display(*head);

}
