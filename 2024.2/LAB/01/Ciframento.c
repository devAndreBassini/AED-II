#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <ctype.h>

const int key = 3;

char *ciframento(char *word){
    int lenght = strlen(word);

    for(int i = 0; i < lenght; i++){
        char newLetter = word[i] + key;

        if(word[i] == tolower(word[i])){
            if(newLetter%'a' > 25)
            word[i] = 'a' + (newLetter%'a' - 26);
        } else if(word[i] == toupper(word[i])){
            if(newLetter%'A' > 25)
            word[i] = 'A' + (newLetter%'A' - 26);
        }

        word[i] = newLetter;
    }  
    return word;  
}

int main(){
    char word[80];

    while(true){
        scanf(" %[^\n]", word);

        if(strcmp(word, "FIM") == 0)
            break;

        letter(word);
        // printf("%s\n", ciframento(word));
    }
    return 0;
}