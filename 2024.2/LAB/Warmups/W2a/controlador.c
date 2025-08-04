#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    int side;
    char id[10];
} Plane;

// Variáveis Globais
int planes_size = 1;
Plane *planes = NULL;

// Get Input Function
void getInput() {
    int pista = 0;

    planes = malloc(planes_size * sizeof(Plane));
    if (planes == NULL) exit(1);

    scanf("%d", &pista);
    while (pista != 0) {
        Plane *temp = realloc(planes, planes_size * sizeof(Plane));
        if (temp == NULL) {
            free(planes);
            exit(1);
        }

        planes = temp;
        planes[planes_size - 1].side = pista;

        scanf("%s", planes[planes_size - 1].id);
        scanf("%d", &pista);

        planes_size++;
    }
}

void ordenarCustom(Plane *main) {
    int order[] = {-1, -3, -2, -4};
    int *used = calloc(planes_size, sizeof(int));
    int pos = 0;
    Plane *organized = malloc(planes_size * sizeof(Plane));

    if (used == NULL) exit(1);

    if (organized == NULL) {
        free(used);
        exit(1);
    }

    while (pos < planes_size - 1) {
        for (int i = 0; i < 4; i++) {
            int atual = order[i];
            for (int j = 0; j < planes_size - 1; j++) {
                if (!used[j] && main[j].side == atual) {
                    organized[pos++] = main[j];
                    used[j] = 1;
                    break;
                }
            }
        }
    }

    for (int i = 0; i < planes_size - 1; i++) {
        main[i] = organized[i];
    }

    free(used);
    free(organized);
}

void printPlanes(){
    for (int i = 0; i < planes_size - 1; i++) {
        printf("%s ", planes[i].id);
    }
    printf("\n");
}

int main() {
    getInput();
    // printPlanes();
    ordenarCustom(planes);
    printPlanes();    

    free(planes);
    return 0;
}
