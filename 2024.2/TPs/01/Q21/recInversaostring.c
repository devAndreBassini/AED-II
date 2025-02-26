#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void inverterString(const char *str, int index) {
    if (index < 0) {
        putchar('\n');
        return;
    }
    putchar(str[index]);
    inverterString(str, index - 1);
}

int main() {
    char input[800];

    while (fgets(input, 800, stdin) != NULL) {
        size_t len = strlen(input);
        if (len > 0 && input[len - 1] == '\n') {
            input[len - 1] = '\0';
            len--;
        }
        
        if (strcmp(input, "FIM") == 0) break;
        
        inverterString(input, len - 1);
    }
    
    return 0;
}
