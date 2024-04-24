#include <stdlib.h>
#include <stdio.h>
#include <string.h>

typedef struct{
    char nome[20];
    char assinatura[20];
} Aluno;

int main(){
    int N = -1;
    int M = 0;
    int dif = 0;
    int falsas = 0;

    while(N != 0){
        scanf("%i", &N);
        if(N < 1 || N > 50 || N == 0){ return 0;}
        Aluno original[N];

        for(int i = 0; i < N; i++){
            scanf("%s", original[i].nome);
            scanf("%s", original[i].assinatura);
        }

        scanf("%i", &M);
        if(M < 0 || M > N){ return 0;}
        Aluno aula[M];
        for(int j = 0; j < M; j++){
            scanf("%s", aula[j].nome);
            scanf("%s", aula[j].assinatura);
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                dif = 0;
                if(strcmp(original[i].nome, aula[j].nome) == 0){
                    for(int l = 0; l < strlen(original[i].assinatura); l++){
                        if(original[i].assinatura[l] != aula[j].assinatura[l]){
                            dif++;
                        }
                    }
                    i++;
                }
                if(dif > 1){
                    falsas++;
                }  
            }
        }
        printf("%i\n", falsas);
    }
    return 0;
}