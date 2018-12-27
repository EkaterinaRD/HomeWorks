#include "header.h"

void main(void) {

	memory = (int *)malloc(sizeof(int) * MAX);
	if (memory == NULL) {
		printf("memory allocation failed\n");
		exit(1);
	}

	stack = (stack_t *)malloc(sizeof(stack_t));
	if (stack == NULL) {
		printf("stack_t pointer allocation failed\n");
		exit(1);
	}
	stackCreate(stack, MAX);

	function = (func_list *)malloc(sizeof(func_list) * MAX_INSTR);
	if (function == NULL) {
		printf("fuction list allocation failed\n");
		exit(1);
	}

	program = (instr_t *)malloc(sizeof(instr_t) * MAX_INSTR);
	if (program == NULL) {
		printf("program malloc failed\n");
		exit(1);
	}

	FILE *fp;
	fopen_s(&fp, "prog.asm", "r");
	if (fp == NULL) {
		printf("couldn't open file\n");
		exit(1);
	}

	char *line = (char *)malloc(500 * sizeof(char));
	if (line == NULL) {
		printf("line malloc file\n");
		exit(1);
	}

	int num = 0, n = 0;

	while (fgets(line, MAX_LINE_LENGTH, fp) != NULL) {
		char *cut = strchr(line, ';');
		if (cut != NULL) {
			*cut = '\0';
		}

		char *cur_instr = (char *)malloc(sizeof(char) * MAX_INSTR);
		char *cur_arg = (char *)malloc(sizeof(char) * MAX_INSTR);
		if (cur_instr == NULL || cur_arg == NULL) {
			printf("allocation for buffer faild\n");
		}

		sscanf(line, "%s %s\n", cur_instr, cur_arg);

		if (strcmp(cur_instr, "ldc") == 0) {
			program[num].type = LDC;
			program[num].arg = atoi(cur_arg);
		}
		else if (strcmp(cur_instr, "add") == 0) {
			program[num].type = ADD;
		}
		else if (strcmp(cur_instr, "sub") == 0) {
			program[num].type = SUB;
		}
		else if (strcmp(cur_instr, "cmp") == 0) {
			program[num].type = CMP;
		}
		else if (strcmp(cur_instr, "ld") == 0) {
			program[num].type = LD;
			program[num].arg = atoi(cur_arg);
		}
		else if (strcmp(cur_instr, "st") == 0) {
			program[num].type = ST;
			program[num].arg = atoi(cur_arg);
		}
		else if (strcmp(cur_instr, ":") == 0) {
			program[num].type = LAB;
			function[n].f_name = cur_arg;
			function[n].f_line = num;
			n++;
		}
		else if (strcmp(cur_instr, "jmp") == 0) {
			program[num].type = JMP;
			program[num].label = cur_arg;
		}
		else if (strcmp(cur_instr, "br") == 0) {
			program[num].type = BR;
			program[num].label = cur_arg;
		}
		else if (strcmp(cur_instr, "ret") == 0) {
			program[num].type = RET;
		}
		else {
			for (size_t i = 0; i < strlen(cur_instr); i++) {
				if (!isspace(cur_instr[i])) {
					printf("unrecognized command: %s\n", cur_instr);
					exit(1);
				}
			}
		}

		num++;
	}

	fclose(fp);
	
	int ip = 0x0;
	while (1) {
		switch (program[ip].type) {
		case LDC:
			_ldc(program[ip].arg);
			break;
		case ADD:
			_add();
			break;
		case SUB:
			_sub();
			break;
		case CMP:
			_cmp();
			break;
		case LD:
			_ld(program[ip].arg);
			break;
		case ST:
			_st(program[ip].arg);
			break;
		case LAB:
			break;
		case JMP:
			ip = _jmp(program[ip].label, n);
			break;
		case BR:
			ip = _br(program[ip].label, n, ip);
			break;
		case RET:
			printf("Program is finishing\nStack: ");
			while (!stackIsEmpty(stack)) {
				printf("%d ", pop(stack));
			}
			printf("\n");
			system("pause");
			exit(0);
			break;
		default:
			printf("Unknown error\n");
			exit(1);
			break;
		}
		ip++;
	}

	free(line);                
	free(program);             
	free(function);
	stackClear(stack);    
	free(stack);
	free(memory);              

}