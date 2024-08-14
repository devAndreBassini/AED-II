#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

FILE *file;

bool ehPalindromo(char *str) {
    int size = strlen(str) - 2;

    for (int i = 0; i <= size; i++) {
        if (str[i] != str[size - i])
            return false;
    }
    return true;
}

int main() {
    char str[80];

    file = fopen("pub.in", "r");
    while(true){
        fgets(str, sizeof(str), file);

        if ((strcmp(str, "FIM") == 0) || feof(file))
            return 0;

        if (ehPalindromo(str))
            printf("SIM\n");
        else
            printf("NAO\n");
    }
    fclose(file);
    return 0;
}