#include <stdio.h>

int main(){

    int a = 0, b = 0, c;
    int i;

    for (i = 1; i < 101; i++) {
        a = a + (i * i);
    }

    for (i = 1; i < 101; i++) {
        b = b + i;
    }
    b = b * b;

    c = b - a;
    printf("b - a = %d - %d = %d\n", b, a, c);
    return 0;
}
