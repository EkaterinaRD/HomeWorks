#include <stdio.h>

int main(){
    FILE *f = fopen("hello.txt", "r");
    if (f == NULL){
        printf("I didn't find this file\n");
        return 0;
    }

    char c = fgetc(f), p;
    while (c != EOF){
        p = c;
        c = fgetc(f);
        if (p == '/' && c == '/'){
            printf("//");
            c = fgetc(f);
            while (c != '\n' && c != EOF){
                printf("%c", c);
                c = fgetc(f);
            }
            printf("\n");
        }
    }
    fclose(f);
    return 0;
}