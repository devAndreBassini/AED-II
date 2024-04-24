#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define MAX_SIZE 1000

char* substituirLetras(const char* texto, char letraOriginal, char letraSubstituta) {
    char* resultado = (char*)malloc((strlen(texto) + 1) * sizeof(char));
    if (resultado == NULL) {
        printf("Erro de alocação de memória.\n");
        exit(1);
    }

    int i;
    for (i = 0; texto[i] != '\0'; i++) {
        if (texto[i] == letraOriginal) {
            resultado[i] = letraSubstituta;
        } else {
            resultado[i] = texto[i];
        }
    }
    resultado[i] = '\0';

    return resultado;
}

int main() {
    srand(4);

    char entrada[MAX_SIZE];
    char end[] = "FIM";

    do {
        fgets(entrada, MAX_SIZE, stdin);
        entrada[strcspn(entrada, "\n")] = '\0';

        if (strcmp(entrada, end) != 0) {
            char letraOriginal = 'a' + (rand() % 26);
            char letraSubstituta = 'a' + (rand() % 26);

            char* resultado = substituirLetras(entrada, letraOriginal, letraSubstituta);
            printf("%s\n", resultado);
            free(resultado);
        }
    } while (strcmp(entrada, end) != 0);

    return 0;
}
