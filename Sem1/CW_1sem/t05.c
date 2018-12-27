#include <stdio.h>
#include <string.h>

#define  SIZE 50

int main(){

    FILE *f = fopen("hello.txt", "r");
    if (f == NULL) {
        printf("I didn't find this file\n");
        return 0;
    }

    char str1[SIZE], str2[SIZE] = {0};
    int n = 1, j = 0, len, i;
    char c = fgetc(f);
    while (c != EOF) {
        if (c != '\n'){
            str1[j] = c;
            j++;

        }
        else if (c == '\n') {
            int len = strlen(str1);

            for (i = 0; i < len; i++) {
                str2[i] ^= str1[i];
            }
            n++;
            j = 0;
        }
        c = fgetc(f);
    }
    printf("%d\n%s\n", n, str2);
    fclose(f);
    return 0;
}
