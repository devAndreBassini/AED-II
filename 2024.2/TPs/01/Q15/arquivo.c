#include <stdio.h>
#include <stdlib.h>

#define NOME_ARQUIVO "numeros.txt"

int main() {
    FILE *arquivo;
    int n;
    
    scanf("%d", &n);

    arquivo = fopen(NOME_ARQUIVO, "wb");
    if (arquivo == NULL) {
        perror("Erro ao abrir o arquivo");
        return 1;
    }

    for (int i = 0; i < n; i++) {
        double numero;
        scanf("%lf", &numero);
        fwrite(&numero, sizeof(double), 1, arquivo);
    }

    fclose(arquivo);

    arquivo = fopen(NOME_ARQUIVO, "rb");
    if (arquivo == NULL) {
        perror("Erro ao abrir o arquivo");
        return 1;
    }

    fseek(arquivo, 0, SEEK_END);
    long posicaoAtual = ftell(arquivo);

    for (int i = n - 1; i >= 0; i--) {
        posicaoAtual -= sizeof(double);
        fseek(arquivo, posicaoAtual, SEEK_SET);
        double numero;
        fread(&numero, sizeof(double), 1, arquivo);

        char buffer[64];
        sprintf(buffer, "%.10f", numero);

        char *ptr = buffer;
        while (*ptr) ptr++;
        while (*(ptr - 1) == '0') ptr--;
        if (*(ptr - 1) == '.') ptr--;
        *ptr = '\0';

        printf("%s\n", buffer);
    }

    fclose(arquivo);
    return 0;
}
