#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int getMaiusculas(const char *input, int i) {
    if (i >= strlen(input)) return 0;

    return (input[i] >= 65 && input[i] <= 90) + getMaiusculas(input, i + 1);
}

int main() {
    while (1) {
        char input[100];
        scanf(" %[^\n]", input);
        if (strcmp(input, "FIM") == 0) break;

        int count = getMaiusculas(input, 0);

        printf("%i\n", count);
    }
}