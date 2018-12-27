#include "list.h"

void display(LIST *head) {

    if (head == NULL) {
        printf("List is NULL\n");
        return;
    }

    LIST *L;

    for (L = head; L != NULL; L = L->next) {
        printf("%s ", L->value);
    }

    printf("\n");

}

LIST *search_list(LIST *head, char *data) {

    if (head == NULL)
        return NULL;

    if (strcmp(head->value, data) == 0) {
        return head;
    }
    else {
        search_list(head->next, data);
    }

}

LIST *predecessor_list(LIST *head, char *data) {

    if ((head == NULL) || (head->next == NULL)) {
        return NULL;
    }

    if (strcmp(head->next->value, data) == 0) {
        return head;
    }
    else {
        predecessor_list(head->next, data);
    }

}
