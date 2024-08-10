#include <ctype.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

bool ehPalindromo(char *str) {
    int size = strlen(str) - 2;

    for (int i = 0; i <= size; i++) {
        if (tolower(str[i]) != tolower(str[size - i]))
            return false;
    }
    return true;
}

int main() { 
    char str[80];

    while(true){
        fgets(str, sizeof(str), stdin);

        if (strcmp(str, "FIM") == 0)
            return 0;

        if (ehPalindromo(str))
            printf("SIM\n");
        else
            printf("NAO\n");
    }
    return 0;
}