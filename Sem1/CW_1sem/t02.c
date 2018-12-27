#include <stdio.h>
#include <string.h>

#define SIZE 10

int count(char *arr, int len) {

  int res = 0, i, j;

  for (i = len, j = 0; i >= 0, j <= len; i--, j++){
      res = res + ((arr[i] - '0') * (1 << j));
  }

  return res;
}

int main(){

    char a[SIZE], b[SIZE];

    printf("Input 'a' number: ");
    scanf("%s", a);

    int len = strlen(a) - 1;

    int aa = count(a, len);

    printf("Input 'b' number: ");
    scanf("%s", b);

    len = strlen(b) - 1;

    int bb = count(b, len);

    if (aa < bb){
        printf("bb = %d more than aa = %d\n", bb, aa);
    } else {
        printf("aa = %d more than bb = %d\n", aa, bb);
    }

    return 0;
}
