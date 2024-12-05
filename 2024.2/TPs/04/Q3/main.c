#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

typedef struct {
    int ID;
    char nome[100];
} Pokemon;

typedef struct no {
    char nome[100];
    struct no *dir, *esq;
} NO;

Pokemon pokemon;

void getNome(int ID){
    FILE *file = fopen("../pokemon.csv", "r");
    char tmp[200], charID[200];
    fgets(tmp, 200, file);
    do{
        fscanf(file, "%[^,],%[^,],%[^,],%[^\n]", charID, tmp, pokemon.nome, tmp);
        pokemon.ID = atoi(charID);
    }while(pokemon.ID != ID);
    fclose(file);
}

void insert(NO **raiz, char *nome) {
    // printf("%s, ", nome);
    printf("%i, ", pokemon.ID);
    NO *aux = *raiz;
    while (aux) {
        if (strcmp(nome, aux->nome) > 0)
            raiz = &aux->esq;
        else
            raiz = &aux->dir;
        aux = *raiz;
    }
    aux = malloc(sizeof(NO));
    strcpy(aux->nome, nome);
    aux->esq = NULL;
    aux->dir = NULL;
    *raiz = aux;
}

NO* search(NO *raiz, char *nome){
    if(nome[strlen(nome)-1] == '\n')
        nome[strlen(nome)-1] = '\0';

    printf("%s\nraiz ", nome);
    while(raiz){
        if(strcmp(nome, raiz->nome) > 0){
            printf("esq ");
            raiz = raiz->esq;
        } else if(strcmp(nome, raiz->nome) < 0){
            raiz = raiz->dir;
            printf("dir ");
        } else {
            return raiz;
        }
    }
    
    return NULL;
}

void print(NO *raiz) {
    if (raiz) {
        printf("%s\n", raiz->nome);
        print(raiz->esq);
        print(raiz->dir);
    }
}

int main() {
    int vet[51] = {181, 791, 453, 46, 137, 718, 56, 261, 91, 317, 297, 189, 132, 677, 398, 506, 527, 382, 700, 550, 47, 61, 252, 564, 473, 642, 500, 98, 222, 608, 19, 24, 777, 466, 328, 740, 438, 682, 180, 708, 174, 363, 741, 21, 493, 477, 411, 248, 666, 112, 151};
    
    for(int i = 0; i < 51; i++){
        for(int j = i+1; j < 51; j++){
            if(vet[i] > vet[j]){
                int aux = vet[i];
                vet[i] = vet[j];
                vet[j] = aux;
                i = 0;
                break;
            }
        }
    }

    for(int j = 1; j < 51; j++){
        printf("%i\n", vet[j]);
    }
    return 0;

    NO *busca, *raiz = NULL;
    
    char input[100];
    int inputID = 1; 

    while (1) {
        fgets(input, 100, stdin);
        input[strlen(input) - 1] = '\0';

        if(strcmp(input, "FIM") == 0){
            fgets(input, 100, stdin);  
            inputID = 2;
        }

        if(inputID == 1){
            pokemon.ID = atoi(input);
            getNome(pokemon.ID);
            insert(&raiz, pokemon.nome);
        }
        if(inputID == 2){
            if(strcmp(input, "FIM") == 0) break;
            if(search(raiz, input)) printf("SIM\n");
            else printf("NAO\n");
        }       
    }

    print(raiz);
    return 0;
}