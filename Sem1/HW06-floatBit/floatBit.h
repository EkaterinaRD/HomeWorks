#include <stdio.h>

typedef union NUM {

    int inum;
    float fnum;

}NUM;

typedef struct NUM_S {

    unsigned mant: 23;
    unsigned exp: 8;
    unsigned sign: 1;

}NUM_S;

typedef union NUM_U {

    float fu;
    struct numS n;

}NUM_U;

typedef struct NAME_T {

    void (*f1)(NUM );
    void (*f2)(NUM_U);
    void (*f3)(float );

};

void BPrint(int s, int mant, int ex);
void Out1(NUM x);
void Out2(NUM_U x);
void Out3(float x);
