#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <ctype.h>

const int key = 3;

int main(){
    char word[80];

    while(true){
        scanf(" %[^\n]", word);

        if(strcmp(word, "FIM") == 0)
            break;

        for(int i = 0; i < strlen(word); i++){
            word[i] += key;
        }
        
        printf("%s\n", word);
    }
    return 0;
}