#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

bool ehPalindromoRecursivo(char str[], int inicio, int fim) {
    if (inicio >= fim)
        return true;
    if (str[inicio] != str[fim])
        return false;
    return ehPalindromoRecursivo(str, inicio + 1, fim - 1);
}

bool ehPalindromo(char str[]) {
    int tamanho = strlen(str);
    return ehPalindromoRecursivo(str, 0, tamanho - 2);
}

int main() {
    char str[100];
    FILE *arquivo;

    arquivo = fopen("pub.in", "r");
    while (fgets(str, sizeof(str), stdin) != NULL) {
        if (ehPalindromo(str)) {
            printf("SIM\n");
        } else {
            printf("NAO\n");
        }
    }
    fclose(arquivo);
    return 0;
}
