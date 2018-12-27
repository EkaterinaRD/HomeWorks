#include "floatBit.h"

void BPrint(int sign, int mant, int exp) {

   int i;

   if (exp == 255 & mant != 0){
       printf("NaN\n");
       return;
   }

   if (exp == 255 && mant == 0) {
       if (sign == 1)
           printf("-Inf\n");
       else
           printf("+Inf\n");
       return;
   }

   printf("(-1)^%d * 1.", sign);

   for (i = 22; i > 1; i--) {
      printf("%d", (mant >> i) & 1);
   }

   printf(" * 2^%d\n", exp - 127);

}

void Out1(NUM x){

   const int mask = ~(1 << 31);//11111111 11111111111111111111111
   int p;//для выделения положительной части числа
   int sign, exp, mant;//знак, exp, мантисса

   //определение знака числа
   if (x.fnum > 0) {
      sign = 0;
   }
   else {
      sign = 1;
   }

   //выделение +части числа
   p = x.inum & mask;

   //вычисление exp
   exp = p >> 23;

   //вычисление мантиссы
   mant = p - (exp << 23);

   BPrint(sign, mant, exp);

}

void Out2(NUM_U x) {

    int sign, exp, mant;
    sign = x.n.sign;
    exp = x.n.exp;
    mant = x.n.mant;
    BPrint(sign, mant, exp);

}

void Out3(float x) {

   int sign, exp, mant;
   int *newx = (int *)&x;

   sign = *newx >> 31;
   exp = (*newx - (sign << 31)) >> 23;
   mant = (*newx << 9) >> 9;

   BPrint(sign, mant, exp);

}
