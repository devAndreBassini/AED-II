#include <stdio.h>
#include <stdbool.h>
#include <string.h>

int contarMaiusculas(char str[]) {
    int contador = 0;
    for(int i = 0; str[i] != '\0'; i++) {
        if(str[i] >= 65 && str[i] <= 90) {
            contador++;
        }
    }
    return contador;
}

int main() {
    bool end = false;
    char line[1000];
    while (!end && fgets(line, sizeof(line), stdin)) {
        if(strcmp(line, "FIM\n") == 0) {
            end = true;
        } else {
            int maiusculas = contarMaiusculas(line);
            printf("%d\n", maiusculas);
        }
    }
    return 0;
}
