#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <conio.h>
#include <ctype.h>

#define MAX 262144
#define MAX_INSTR 10000
#define MAX_LINE_LENGTH 500

//********************************ASM********************************
typedef struct {
	int *contents;
	int maxSize;
	int top;
} stack_t;

typedef enum {
	LDC,
	ADD,
	SUB,
	CMP,
	LD,
	ST,
	LAB,
	JMP,
	BR,
	RET
} instr_type;

typedef struct {
	instr_type type;
	int arg;
	char *label;
} instr_t;

typedef struct {
	char *f_name;
	int f_line;
} func_list;

stack_t *stack;
instr_t *program;
int *memory;
func_list *function;

//*******************************STACK********************************
void stackCreate(stack_t *stack, int maxSize);
void stackClear(stack_t * stack);
int stackIsEmpty(stack_t *stack_p);
void push(stack_t *stack, int argument);
int pop(stack_t * stack_p);

void displayStatusStack();
//****************************INSTRUCTION******************************
void _ldc(int num);
void _add();
void _sub();
void _cmp();
void _ld(int address);
void _st(int address);
int _jmp(char *arg, int size_list);
int _br(char *arg, int size_list, int num_line);