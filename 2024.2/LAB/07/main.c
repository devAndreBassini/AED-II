#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define fprintf  //

int main() {
    FILE *f = fopen("pub.in", "r");  // O(1)
    if (f == NULL) exit(1);          // O(1)

    int N, output;  // O(1)

    // O loop principal que itera enquanto houver inteiros a serem lidos
    while (fscanf(f, "%d", &N) != EOF) {  // O(N) no pior caso
        int gridL[N], gridC[N],
            max = N - 1;              // O(1) para inicialização de variáveis
        int tmp, pos, overtakes = 0;  // O(1)

        // Lendo N valores para gridL
        for (int i = 0; i < N; i++) {    // O(N)
            fscanf(f, "%d", &gridL[i]);  // O(1) para cada leitura
        }

        // Lendo N valores para gridC
        for (int i = 0; i < N; i++) {    // O(N)
            fscanf(f, "%d", &gridC[i]);  // O(1) para cada leitura
        }

        // Analisando as operações de ultrapassagem
        for (int i = 0; i < N; i++) {  // O(N)
            fprintf(stderr, "%i)\nL(%i) - C(%i)\n", i, gridL[i], gridC[i]);

            if (gridL[i] != gridC[i]) {  // O(1)
                // Encontrando a posição de gridC[i] em gridL
                for (int j = 0; gridL[j] != gridC[i];
                     j++) {  // O(N) no pior caso
                    fprintf(stderr, "L(%i) - C(%i)\n", gridL[j], gridC[i]);

                    if (gridL[j + 1] == gridC[i]) {  // O(1)
                        pos = j + 1;                 // O(1)
                        overtakes += j + 1 - i;      // O(1)
                    }
                }

                // Realizando a movimentação dos elementos em gridL
                for (int j = pos; j > i; j--) {  // O(N) no pior caso
                    gridL[j] = gridL[j - 1];     // O(1)
                }
            }
        }

        printf("%i\n", overtakes);  // O(1)
        overtakes = 0;              // O(1)
    }
    fclose(f);  // O(1)
    return 0;   // O(1)
}
