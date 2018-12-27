#include "list.h"

int main() {

    LIST *head = NULL;
    LIST *end = head;
    num = 0;
    c = 0;
    char str[10];
    int quit = 0;
    char k;

    printf( "!!: string no more then 10 symbols.\n"
            "a - exit\n"
            "b - push_front\n"
            "c - push_back\n"
            "d - insert_after\n"
            "e - delete_element\n"
            "f - delete_list\n"
            "g - reverse\n"
            "h - display\n"
            "i - create_cycle\n"
            "j - test_list\n"
            "Choose opinion: ");

    while (!quit) {

        scanf("%c", &k);
        switch (k) {

        case 'a':
            quit = 1;
            if (c != 1) {
                delete_list(&head, &end);
            }
            else {
                end->next = NULL;
                delete_list(&head, &end);
            }
            break;
        case 'b':
            printf("Input str: ");
            scanf("%s", str);
            push_front(&head, &end, str);
            printf("Choose opinion: ");
            break;
        case 'c':
            printf("Input str: ");
            scanf("%s", str);
            push_back(&head, &end, str);
            printf("Choose opinion: ");
            break;
        case 'd':
            printf("Input str: ");
            scanf("%s", str);
            insert_after(&head, &end, str);
            printf("Choose opinion: ");
            break;
        case 'e':
            printf("Input str: ");
            scanf("%s", str);
            delete_element(&head, &end, str);
            printf("Choose opinion: ");
            break;
        case 'f':
            delete_list(&head, &end);
            printf("Choose opinion: ");
            break;
        case 'g':
            reverse(&head, &end);
            printf("Choose opinion: ");
            break;
        case 'h':
            display(head);
            printf("Choose opinion: ");
            break;
        case 'i':
            printf("Input str: ");
            scanf("%s", str);
            create_cycle(head, &end, str);
            printf("Choose opinion: ");
            break;
        case 'j':
            test_list(head);
            printf("Choose opinion: ");
            break;

        }

    }

    return 0;
}
