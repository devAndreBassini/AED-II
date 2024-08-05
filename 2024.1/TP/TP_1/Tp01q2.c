#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

bool ehPalindromo(char str[]){
    int i = 0, j = strlen(str)-2;
    while(i < strlen(str)/2){
        if(str[i] != str[j]){
            return false;
        }
        i++;
        j--;
    }
    return true;
}

int main(){
    char str[100];
    FILE *arquivo;

    arquivo = fopen("pub.in", "r");
    while(fgets(str, sizeof(str), stdin)!=NULL){
       if(ehPalindromo(str)){
            printf("SIM\n");
        } else {
            printf("NAO\n");
        }
    }
    fclose(arquivo);
    return 0;
}