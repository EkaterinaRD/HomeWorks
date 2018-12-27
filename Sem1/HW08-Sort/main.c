#include "sort.h"

int main(int argc, char const *argv[]) {

  clock_t start_func = clock();

  FILE *fp;
  fp = fopen ("sort.txt","w");

  long int n = 5;
  int i = 1;

//////////////////////////////////////5 - elements
  printf("Wait...\n");

  int *arr1 = malloc(n * sizeof(int));
  int *arr2 = malloc(n * sizeof(int));
  int *arr3 = malloc(n * sizeof(int));

  if (arr1 == NULL) {
    printf("non Memmory - arr1\n");
    return 0;
  }
  else if (arr2 == NULL) {
    printf("non Memmory - arr2\n");
    return 0;
  }
  else if(arr3 == NULL) {
    printf("non Memmory - arr3\n");
    return 0;
  }

  fillRand(arr1, n);
  fillRand(arr2, n);
  fillRand(arr3, n);

  clock_t start = clock();
  BubbleSort(arr1, n);
  clock_t finish = clock();
  double t1 = (double)(finish -  start) / CLOCKS_PER_SEC;

  start = clock();
  QuickSort(arr2, 0, n - 1);
  finish = clock();
  double t2 = (double)(finish -  start) / CLOCKS_PER_SEC;

  start = clock();
  CountSort(arr3, n);
  finish = clock();
  double t3 = (double)(finish -  start) / CLOCKS_PER_SEC;

  free(arr3);
  free(arr2);
  free(arr1);

  fprintf(fp, "%10s%20s%20s%20s\n", "n", "BubbleSort", "QuickSort", "CountSort");
  fprintf(fp, "%10ld%20lf%20lf%20lf\n", n, t1, t2, t3);

//////////////////////////////////////

  for (n = 10; n <= 100000000; n *= 10) {

    if (n <= 10000) {

      arr1 = malloc(n * sizeof(int));
      arr2 = malloc(n * sizeof(int));
      arr3 = malloc(n * sizeof(int));

      if (arr1 == NULL) {
        printf("non Memmory - arr1\n");
        return 0;
      }
      else if (arr2 == NULL) {
        printf("non Memmory - arr2\n");
        return 0;
      }
      else if (arr3 == NULL) {
        printf("non Memmory - arr3\n");
        return 0;
      }

      fillRand(arr1, n);
      fillRand(arr2, n);
      fillRand(arr3, n);

    }
    else if (n >= 100000 && n <= 10000000){

      arr2 = malloc(n * sizeof(int));
      arr3 = malloc(n * sizeof(int));

      if (arr2 == NULL) {
        printf("non Memmory - arr2\n");
        return 0;
      }
      else if (arr3 == NULL) {
        printf("non Memmory - arr3\n");
        return 0;
      }

      fillRand(arr2, n);
      fillRand(arr3, n);
    }
    else {

      arr3 = malloc(n * sizeof(int));

      if (arr3 == NULL) {
        printf("non Memmory - arr3\n");
        return 0;
      }

      fillRand(arr3, n);

    }

    if (n <= 10000) {

      start = clock();
      BubbleSort(arr1, n);
      finish = clock();
      t1 = (double)(finish -  start) / CLOCKS_PER_SEC;

    }
    else {
      t1 = 0;
    }

    if (n <= 10000000) {

      start = clock();
      QuickSort(arr2, 0, n - 1);
      finish = clock();
      t2 = (double)(finish -  start) / CLOCKS_PER_SEC;

    }
    else {
      t2 = 0;
    }

    start = clock();
    CountSort(arr3, n);
    finish = clock();
    t3 = (double)(finish -  start) / CLOCKS_PER_SEC;

    free(arr3);
    if (n <= 10000000) {
      free(arr2);
    }
    if (n <= 10000) {
      free(arr1);
    }

    fprintf(fp, "%10ld", n);
    if (t1 == 0) {
      fprintf(fp, "%20s", "n/a");
    }
    else {
      fprintf(fp, "%20lf", t1);
    }

    if (t2 == 0) {
      fprintf(fp, "%20s", "n/a");
    }
    else {
      fprintf(fp, "%20lf", t2);
    }

    fprintf(fp, "%20lf\n", t3);
  }

  fprintf(fp, "For 10^5 elements time of bubble sort ~166 s\n");
  fprintf(fp, "For 10^8 elements time of quick sort >5 min\n");

/////////////////////////////////////
  fclose (fp);

  printf("Finish =)\n");

  clock_t finish_func = clock();
  printf("Time work of programm: %lf s.\n", (double)(finish_func - start_func) / CLOCKS_PER_SEC);

  return 0;
}
