#include <stdio.h>
#include <string.h>

int main(){
    int i = 0;
    char str[228];
    char k;
    printf("Input string no more then 228 symbols and symbol: ");
    scanf("%s %c", str, &k);
    while(str[i]){
        if (str[i] != k)
            printf("%c", str[i]);
        i++;
    }
    printf("\n");

    return 0;
}