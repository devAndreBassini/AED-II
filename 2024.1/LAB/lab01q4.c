#include <stdio.h>
#include <ctype.h>

int contarMaiusculas(char str[], int index) {
    if (str[index] == '\0') {
        return 0;
    }
    int contador = isupper(str[index]) ? 1 : 0;
    return contador + contarMaiusculas(str, index + 1);
}

int main() {
    char line[1000];
    while (1) {
        fgets(line, 1000, stdin);
        if (line[0] == 'F' && line[1] == 'I' && line[2] == 'M') {
            break;
        } else {
            int maiusculas = contarMaiusculas(line, 0);
            printf("%d\n", maiusculas);
        }
    }
    return 0;
}
