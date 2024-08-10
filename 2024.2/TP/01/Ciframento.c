#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <ctype.h>

char *ciframento(char *word, int key){
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
    int key = -1;

    while(true){
        scanf("%i", &key);
        
        if(key == 0)
            break;cd;cd code/AED-II/;clear
            cd;cd code/AED-II/;clear
            

        scanf(" %[^\n]", word);

        printf("%s\n", word);
        key = 0;
    }
    return 0;
}