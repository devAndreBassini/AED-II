#include <ctype.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

bool X1(char *str) {
    int cont = 0;
    for (int i = 0; i < strlen(str); i++) {
        if (65 <= str[i] && str[i] <= 122) {
            char C = tolower(str[i]);
            if (C == 'a' || C == 'e' || C == 'i' || C == 'o' || C == 'u')
                cont++;
        }
    }
    if (cont == strlen(str) - 1) return true;

    return false;
}

bool X2(char *str) {
    int cont = 0;
    for (int i = 0; i < strlen(str); i++) {
        if (65 <= str[i] && str[i] <= 122) {
            char C = tolower(str[i]);
            if (!(C == 'a' || C == 'e' || C == 'i' || C == 'o' || C == 'u'))
                cont++;
        }
    }
    if (cont == strlen(str) - 1) return true;

    return false;
}

bool X3(char *str) {
    int cont = 0;
    for (int i = 0; i < strlen(str); i++) {
        if (48 <= str[i] && str[i] <= 57) cont++;
    }
    if (cont == strlen(str) - 1) return true;

    return false;
}

bool X4(char *str) {
    int cont = 0;
    bool decimal;
    for (int i = 0; i < strlen(str); i++) {
        if (str[i] == ',') decimal = true;
    }
    if (!(decimal)) {
        return false;
    }
    for (int i = 0; i < strlen(str); i++) {
        if ((48 <= str[i] && str[i] <= 57)) cont++;
    }
    if (cont == strlen(str) - 2) return true;

    return false;
}

int main() {
    char str[80];

    while (true) {
        fgets(str, sizeof(str), stdin);
        if (strcmp(str, "FIM") == 0) break;

        X1(str) ? printf("SIM ") : printf("NAO ");
        X2(str) ? printf("SIM ") : printf("NAO ");
        X3(str) ? printf("SIM ") : printf("NAO ");
        X4(str) ? printf("SIM "): printf("NAO ");
    }
    return 0;
}