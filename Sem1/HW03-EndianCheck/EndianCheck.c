#include <stdio.h>

int main() {

        union {
	  int i;
	  char c[sizeof(int)];
        } endian;

	endian.i = 1;
	printf("%s\n\n", endian.c == 0 ? "Big-endian" : "Little-endian");

	return 0;
}