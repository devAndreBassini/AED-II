#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <math.h>

#define endKey "FIM"

void *alteracao(char *word){
    char rand1 = 97 + rand()%25;
    char rand2 = 97 + rand()%25;

    printf("(%c -> %c): ", rand1, rand2);
    for(int i = 0; i < strlen(word); i++){
        if(word[i] == rand1){
            word[i] = rand2;
        }
    }    
}

int main(){
    char word[80];

    // strcpy(word, "abcdefghijklmnopqrstuvwxyz");
    // for(int i = 0; i < 5; i++){
    while(true){
        scanf(" %[^\n]", word);

        if(strcmp(word, endKey) == 0){
            break;
        }

        alteracao(word);

        printf("%s\n", word);
    }
    return 0;
}