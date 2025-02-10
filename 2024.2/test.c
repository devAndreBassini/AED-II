#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

#include <ctype.h>

int main(){
    int i, j, z;
    while(true){
        char input[200];
        scanf("%[^\n]\n", input);
        if(strcmp(input, "0") == 0) break;
        
        // Separação das partes da equação

        // PARTE 1
        int var = input[0] - '0';
        int valuesVar[var];
        
        // PARTE 2
        int startExpression = 0;
        for(i = 2, j = 0; j < var; i+=2, j++){
            valuesVar[j] = input[i] - '0';
        }
        startExpression = i;

        // PARTE 3
        for(i = startExpression, j = 0; j < strlen(input); i++, j++){
            if(input[i] == ' ') i++;
            input[j] = input[i];      
        }

        if(input[j-1] == ' ') j++;
        input[j] = '\0';
        

        // PRINT DOS VALORES
        printf("%i\n", var);
        for(i = 0; i < var; i++){
            printf("%i ", valuesVar[i]);
        }
        printf("\n%s\n\n", input);
        
        // Converter escrita em operadores


    }
    return 0;
}
