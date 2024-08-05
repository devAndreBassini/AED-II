#include <ctype.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char *ToUpper(char *str, int size) {
    for (int i = 0; i < size; i++) {
        str[i] = toupper(str[i]);
    }
    return str;
}

bool ehPalindromo(char *str, int size) {
    bool condition = false;

    strcpy(str, ToUpper(str, size));
    for (int i = 0; i < size; i++) {
        if (str[i] == str[size - i - 1])
            condition = true;
        else
            condition = false;
    }
    return condition;
}

int main() {
    char endKey[80] = "FIM";
    char str[80];
    int size = strlen(str);

    do {
        scanf(" %[^\n]", str);
        int size = strlen(str);

        ToUpper(str, size);
        if (strcmp(str, endKey) == 0) {
            return 0;
        }

        if (ehPalindromo(str, size))
            printf("SIM\n");
        else
            printf("NAO\n");

    } while (strcmp(str, endKey) != 0);
    return 0;
}