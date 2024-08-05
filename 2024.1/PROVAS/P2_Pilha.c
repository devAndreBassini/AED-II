/*
 * Variables:
 *  N = nº commands --> (1 <= N <= 106)
 *  Command Types:
 *      "PUSH V" = add to stack --> V = fun rate --> (1 <= V <= 109)
 *      "POP" = remove from stack
 *      "MIN" = lower value of current stack
 *
 * Input:
 *  First line = N
 *  Other lines = commands
 *
 * Output:
 *  Single line containing the lower value
 *      OR
 *  "EMPTY" string for "POP" and "MIN" commands with empty stack
 *
 */

#include <ctype.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_N 106
#define MAX_V 109

char *commandsList[] = {"PUSH", "POP", "MIN"};

bool checkValidation(char letter, int x) {
    letter = toupper(letter);
    if (x < 1) return false;
    if (letter == 'N' && x <= MAX_N) return true;
    if (letter == 'V' && x <= MAX_V) return true;
    if (letter == '0' && x != 0) return true;
    return false;
}

int getLowerValue(int *array) {
    int x = MAX_V;
    for (int i = 0; i < sizeof(array); i++) {
        if (array[i] < x && array[i] != 0) {
            x = array[i];
        }
    }
    return x;
}

void fillWithZero(int *array) {
    for (int i = 0; i < sizeof(array); i++) {
        array[i] = 0;
    }
}

int main() {
    int N = 0;
    int V = 0;
    int x = 0;

    char input[4];

    scanf("%d", &N);
    int stack[N];

    fillWithZero(stack);

    if (checkValidation('N', N)) {
        for (int i = 0; i < N; i++) {
            scanf("%s", input);
            if (strcmp(input, commandsList[0]) ==
                0) {  // commandsList[0] = PUSH
                scanf("%i", &V);
                if (checkValidation('V', V)) {
                    stack[x] = V;
                    x++;
                }
            }
            if (strcmp(input, commandsList[1]) == 0) {  // commandsList[1] = POP
                x--;
                if (checkValidation('0', stack[x]))
                    stack[x] = 0;
                else
                    printf("EMPTY\n");
            }
            if (strcmp(input, commandsList[2]) == 0) {  // commandsList[2] = MIN
                if (checkValidation('0', stack[x - 1]))
                    printf("%i\n", getLowerValue(stack));
                else
                    printf("EMPTY");
            }
        }
    }
    return 0;
}