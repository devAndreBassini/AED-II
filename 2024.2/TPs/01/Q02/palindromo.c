#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

bool ehPalindromo(char *str) {
    int size = strlen(str);
    for (int i = 0; i < size; i++) {
        if (str[i] != str[size - (i+1)])
            return false;
    }
    return true;
}

int main() {
    
    while(true){
        char str[800] = {""};
        
        scanf("%[^\n]\n", str);
        str[strlen(str)] = '\0';
        if(strcmp(str, "FIM") == 0) break;
        printf("[%s]", str);
        for(int i = 0; i < strlen(str); i++){
            if(!(33 <= str[i] && str[i] <= 255)){
                int j = i;
                do{
                    str[j] = str[j + 1];
                    j++;
                }while(j < strlen(str));
            }
        }

        if (ehPalindromo(str) ? printf("SIM\n"):printf("NAO\n"));

    }
    return 0;
}