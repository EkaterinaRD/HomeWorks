#include <stdio.h>

int strlen(char *s) {

	int c = 0;
	while (s[c] != '\0')
		c++;

	return c;

}

char *strcat(char *dst, char *src)
{
	char* res = dst;
	while (*dst)
		dst++;
	while (*src) {
		*dst = *src;
		dst++;
		src++;
	}
	*dst = '\0';
	return res;
}

void strcpy(char *dst, char *src) {
	while (*src)
		*dst++ = *src++;
	*dst = '\0';

}

int strcmp(char *s1, char *s2){

    int i = 0, p;

    while (s1[i] == s2[i]) {

        if (s1[i] == '\0') 
            break;
   
        i++;
    }

    p = s1[i] - s2[i];

    if (p > 0)
        return 1;
    else if (p < 0)
        return -1;
    else 
        return 0;
}

void main() {

	char s1[51];
	char s2[51];
	char s3[51];

	printf("Input line s1: \n");
	gets_s(s1, 50);
	printf("Result strlen(): %i\n", strlen(s1));

	printf("Re-type the line s1: \n");
	gets_s(s1, 50);
	printf("Input line s2: \n");
	gets_s(s2, 50);
	printf("Result strcat(): %s\n", strcat(s1, s2));

	printf("Re-type the line s1: \n");
	gets_s(s1, 50);
	strcpy(s3, s1);
	printf("Line s1 was copy in line s3 (Result strcpy()): ");
	puts(s3);
	printf("\n");

	printf("Re-type the line s1: \n");
	gets_s(s1, 50);
	printf("Input line s2: \n");
	gets_s(s2, 50);
	printf("Result strcmp(): %d\n", strcmp(s1, s2));
}
