#include "floatBit.h"

int main ()
{
	  NUM a, b;
    NUM_U aa, bb;

	  float bbb;

	  int quit = 0;
	  char c;

	  printf("Input number a: ");
	  scanf("%f", &a.fnum);
	  aa.fu = a.fnum;
	  printf("Input number b: ");
	  scanf("%f", &b.fnum);
	  bb.fu = b.fnum;

	  b.fnum = a.fnum / b.fnum;
	  bb.fu = aa.fu / bb.fu;
	  bbb = b.fnum;

	  printf("0 - exit\n"
		       "1 - Out1\n"
		       "2 - Out2\n"
		       "3 - Out3\n");

		NAME_T x;
		x.f1 = &Out1;
		x.f2 = &Out2;
		x.f2 = &Out3;

    while(!quit){

	      scanf("%c", &c);
				if (c == 0) {
				    quit = 1;
				}
				else if (c == 1) {
					  x.f1(b);
				}
				else if (c == 2) {
					  x.f2(bb);
				}
				else if (c == 3) {
					  x.f3(bbb);
				}
				else {
				    printf("Error\n");
				}

	}

	return 0;

}
