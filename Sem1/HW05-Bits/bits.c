#include <stdio.h>

int addOK(int x, int y);
int bang(int x);
int bitAnd(int x, int y);
int bitXor(int x, int y);
int conditional(int x, int y, int z);
int fitsBits(int x, int n);
int getByte(int x, int n);
int isPower2(int x);
int logicalShift(int x, int n);
int thirdBits();
int sign(int x);

int main() {

    int x, y, z, n;

    printf("1)Input x, y for addOk(x, y): ");
    scanf("%d %d", &x, &y);
    printf("Result addOk(x, y): %d\n\n", addOK(x, y));

    printf("2)Input x for bang(x): ");
    scanf("%d", &x);
    printf("Result bang(x): %d\n\n", bang(x));

    printf("3)Input x, y for bitAnd(x, y): ");
    scanf("%d %d", &x, &y);
    printf("Result for bitAnd(x, y): %d\n\n", bitAnd(x, y));

    printf("4)Input x, y for bitXor(x, y): ");
    scanf("%d %d", &x, &y);
    printf("Result bitXor(x, y): %d\n\n", bitXor(x, y));

    printf("5)Input x, y and z for conditional(x, y, z): ");
    scanf("%d %d %d", &x, &y, &z);
    printf("Result conditional(x): %d\n\n", conditional(x, y, z));

    printf("6)Input x, n for fitsBits(x, n): ");
    scanf("%d %d", &x, &n);
    printf("Result fitsBits(a, n): %d\n\n", fitsBits(x, n));

    printf("7)Input x, n for getByte(x, n): ");
    scanf("%d %d", &x, &n);
    printf("Result getByte(x, n): %d\n\n", getByte(x, n));

    printf("8)Input x for isPower2(x): ");
    scanf("%d", &x);
    printf("Result isPower2(x): %d\n\n", isPower2(x));

    printf("9)Input x, n for logicalShift(x, n): ");
    scanf("%d %d", &x, &n);
    printf("Result logicalShift(x, n): %d\n\n", logicalShift(x, n));

    printf("10)");
    printf("Result thirdBits(): %d\n\n", thirdBits());

    printf("11)Input x for sign(x): ");
    scanf("%d", &x);
    printf("Result sign(x): %d\n\n", sign(x));

    return 0;
}

int addOK(int x, int y) {
    int z, x1, y1, p;

    z = (x + y) >> 31;
    x1 = x >> 31;
    y1 = y >> 31;
    p = !((x1 ^ z) & (y1 ^ z));

    return p;
}

int bang(int x) {
    int k = ~x;
    int z = ~x + 1;
    int p = ((~z & k) >> 31) & 1;

    return p;
}

int bitAnd(int x, int y) {
    int p;
    x = ~x;
    y = ~y;
    p = ~(x | y);

    return p;
}

int bitXor(int x, int y) {
    int k, z, p;
    k = (~x) & y;
    z = x & (~y);
    p = k | z;

    return p;
}

int conditional(int x, int y, int z) {
    int p, k, q;
    k = ((!x) << 31) >> 31;
    q = ~k;
    k = k & z;
    q = q & y;
    p = k | q;

    return p;
}

int fitsBits(int x, int n) {
    int y = 0, z, q, p;
    y =  (~y) << (n + ((~1) + 1));
    z = (x & y) >> (n + ((~1) + 1));
    q = ~z;
    p = !q | !z;

    return p;
}

int getByte(int x, int n) {
    int p = 255;
    n = n << 3;
    x = x >> n;
    p = p & x;

    return p;
}

int isPower2(int x) {
    int p, k, q, c;
    c = !((1 << 31) & x);
    k = !x;
    k = !k;
    q = x + ((~1) + 1);
    p = k & (!(q & x)) & c;

    return p;
}

int logicalShift(int x, int n) {
    
    x = x >> n;
    int c = ~(((1 << 31) >> n) << 1);
    x = x & c;
    return x;
}

int thirdBits() {
    int n = 146, p;
    long int b, c;
    b = n << 8;
    c = n >> 1;
    b = b | c;
    p = (b << 14)| (b >> 1);

    return p;
}

int sign(int x) {
    int a = 1, p, z;
    p = (a << 31) & x;
    z = !x;
    z = !z;
    z = z | (p >> 30);

    return z;
}
