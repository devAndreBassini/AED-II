#include <stdlib.h>
#include <stdio.h>

typedef struct{
    int nota;
    int ID;
} Aluno;

int main(){
    Aluno alunos;


    return 0;
}

/*
Sua classe terá os atributos privado SHOW_ID:string, TYPE:string, TITLE:string, DIRECTOR:string, CAST:string[], COUNTRY:string, DATE_ADDED:date, RELEASE_YEAR:int, RATING:string, DURATION:string, LISTED_IN:string[]. Sua classe também terá pelo menos dois construtores, e os métodos gets, sets, clone, print e read.

O método print mostra os atributos do registro e o read lê os atributos de um registro. Atenção para o arquivo de entrada, pois em alguns registros faltam valores e esse deve ser substituı́do pelo valor NaN. A entrada padrão é composta por várias linhas e cada uma contém um número inteiro indicando o SHOW_ID. A última linha da entrada contém a palavra FIM. A saı́da padrão também contém várias linhas, uma para cada registro contido em uma linha da entrada padrão, no seguinte formato: `[=> id ## type ## title ## director ## [cast] ## country ## date added ## release year ## rating ## duration ## [listed in].`

Exemplo: `=> s1367 ## TV Show ## Disney Mech-X4 ## NaN ## [Kamran Lucas, Nathaniel Potvin, Pearce Joza, Raymond Cham] ## Canada ## NaN ## 2016 ## TV-Y7 ## 2 Seasons ## [Action-Adventure, Comedy, Science Fiction ]`

OBSERVAÇÃO:
• As variáveis do tipo Lista (cast,listed in) deverão ter os seus itens também ORDENA-
DOS.
• Caso algum campo não tenha o seu valor informado, favor atribuir NaN.

*/