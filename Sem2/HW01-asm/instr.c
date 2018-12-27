#include "header.h"

void _ldc(int num) {
	push(stack, num);
}

void _add() {
	int arg_1 = pop(stack);
	int arg_2 = pop(stack);
	int result = arg_1 + arg_2;
	push(stack, result);
}

void _sub() {
	int arg_1 = pop(stack);
	int arg_2 = pop(stack);
	int result = arg_1 - arg_2;
	push(stack, result);
}

void _cmp() {
	int arg_1 = pop(stack);
	int arg_2 = pop(stack);

	if (arg_1 > arg_2) {
		push(stack, 1);
	}
	else if (arg_1 == arg_2) {
		push(stack, 0);
	}
	else {
		push(stack, -1);
	}
}
void _ld(int address) {
	int value = memory[address];
	push(stack, value);
}

void _st(int address) {
	int value = pop(stack);
	memory[address] = value;
}

int _jmp(char *arg, int size_list) {
	
	int i = 0;
	while (i < size_list) {
		if (strcmp(arg, function[i].f_name) == 0) {
			return function[i].f_line - 1;
		}
		i++;
	}
}

int _br(char *arg, int size_list, int num_line) {
	
	int value = pop(stack);
	int line;
	if (value != 0) {
		push(stack, value);
		int i = 0;
			while (i < size_list) {
				if (strcmp(arg, function[i].f_name) == 0) {
					line = function[i].f_line - 1;
					return line;
				}
				i++;
			}
	}
	else {
		line = num_line;
		return line;
	}
	
}