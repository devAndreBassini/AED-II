#include <stdio.h>
#include <stdlib.h>

int main() {
    FILE *arquivo = fopen("valores.txt", "w");
    if (arquivo == NULL) {
        printf("Erro");
        return 1;
    }

    int n;
    scanf("%d", &n);

    for (int i = 0; i < n; i++) {
        double valor;
        scanf("%lf", &valor);
        fprintf(arquivo, "%lf\n", valor);
    }

    fclose(arquivo);

    arquivo = fopen("valores.txt", "r");
    if (arquivo == NULL) {
        printf("Erro");
        return 1;
    }

    fseek(arquivo, 0, SEEK_END);
    long tamanhoArquivo = ftell(arquivo);
    long ponteiro = tamanhoArquivo - 2;

    while (ponteiro >= 0) {
        fseek(arquivo, ponteiro, SEEK_SET);
        char c = fgetc(arquivo);
        if (c == '\n' || ponteiro == 0) {
            char linha[100];
            fgets(linha, 100, arquivo);
            double valor = atof(linha);
            if ((int)valor == valor) {
                printf("%d\n", (int)valor);
            } else {
                printf("%g\n", valor);
            }
        }
        ponteiro--;
    }

    fclose(arquivo);
    return 0;
}
