#include "header.h"

void stackCreate(stack_t *stack_p, int maxSize) {

	int *newContents = (int *)malloc(sizeof(int) * maxSize);
	if (newContents == NULL) {
		printf("Not enough memory to initialize stack\n");
		exit(1);
	}

	stack_p->contents = newContents;
	stack_p->maxSize = maxSize;
	stack_p->top = -1;
}

void stackClear(stack_t * stack_p) {
	
	free(stack_p->contents);
	stack_p->contents = NULL;
	stack_p->maxSize = 0;
	stack_p->top = -1;
}

int stackIsFull(stack_t *stack_p) {
	
	int p = (stack_p->top) >= (stack_p->maxSize - 1);
	return p;
}

int stackIsEmpty(stack_t *stack_p) {
	
	int status_st = stack_p->top;
	int p = status_st < 0;
	return p;
}

void push(stack_t *stack_p, int argument) {
	
	int status_st = stackIsFull(stack_p);
	if (status_st) {
		printf("Pushing erroe: stack is full\n");
		exit(1);
	}

	stack_p->top++;
	int index = stack_p->top;
	stack_p->contents[index] = argument;
}

int pop(stack_t *stack_p) {
	
	int status_st = stackIsEmpty(stack_p);
	if (status_st) {
		printf("Poping erroe: stack is empty\n");
		exit(1);
	}

	int index = stack_p->top;
	stack_p->top--;
	int value = stack_p->contents[index];
	return value;
}

/*void displayStatusStack() {
	stack_t *stack_p;
}*/