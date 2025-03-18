#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(){
    
    while(1){
        char input[100];
        scanf(" %[^\n]", input);
        if(strcmp(input, "FIM") == 0) break;

        int count = 0;
        for(int i = 0; i < strlen(input); i++){
            if(65 <= input[i] && input[i] <= 90)
                count++;
        }   

        printf("%i\n", count);
    }
}