#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include <stdbool.h>

int main(){
    char myString[100];
    int upper;

    while(true){
        scanf(" %[^\n]", myString);
        upper = 0;

        if(strcmp(myString, "FIM") == 0)
            break;
        
        for(int i = 0; i < strlen(myString); i++){
            if(65 <= myString[i] && myString[i] <= 90)
                upper++;            
        }
        printf("%i\n", upper);
    }

    return 0;
}
