#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

FILE *file;

bool ehPalindromo(char *str) {
    int size = strlen(str);
    for (int i = 0; i < size; i++) {
            if (str[i] != str[size - (i+1)])
                return false;
        
    }
    return true;
}

int main() {
    char str[800];

    file = fopen("pub.in", "r");
    while(true){
        fgets(str, sizeof(str), file);
        str[strlen(str)-2] = '\0';
        int size = strlen(str);

        if ((strcmp(str, "FIM") == 0) || feof(file))
            return 0;

        for(int i = 0; i < size; i++){
            if(!(33 <= str[i] && str[i] <= 255 || str[i] == ' ')){
                int j = i;
                do{
                    str[j] = str[j + 1];
                    j++;
                }while(j < size);
            }
        }

        // printf("[%s] > ", str);

        if (ehPalindromo(str))
            printf("SIM\n");
        else
            printf("NAO\n");
    }
    fclose(file);
    return 0;
}