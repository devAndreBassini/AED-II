#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

FILE *file;

bool ehPalindromoRecursivo(char *str, int left, int right) {
    if (left >= right) {
        return true;
    }

    if (str[left] != str[right]) {
        return false;
    }

    return ehPalindromoRecursivo(str, left + 1, right - 1);
}

void verificarPalindromo() {
    char str[80];

    if (fgets(str, sizeof(str), file) == NULL || (strcmp(str, "FIM") == 0) || feof(file)) {
        fclose(file);
        exit(0);
    }

    int size = strlen(str) - 2;

    if (ehPalindromoRecursivo(str, 0, size)) {
        printf("SIM\n");
    } else {
        printf("NAO\n");
    }

    verificarPalindromo();
}

int main() {
    file = fopen("pub.in", "r");
    
    if (file == NULL) {
        printf("Erro ao abrir o arquivo.\n");
        return 1;
    }

    verificarPalindromo();

    return 0;
}
