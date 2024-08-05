#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include <stdbool.h>

int countUpperCase(char* str, int index) {
    if (index == strlen(str)) {
        return 0;
    }

    int count = (isupper(str[index])) ? 1 : 0;

    return count + countUpperCase(str, index + 1);
}

int main() {
    char myString[100];
    int upper;

    while (true) {
        scanf(" %[^\n]", myString);

        if (strcmp(myString, "FIM") == 0)
            break;

        upper = countUpperCase(myString, 0);
        printf("%d\n", upper);
    }

    return 0;
}

// gcc Recursivo.c -o minhaSaida && ./minhaSaida