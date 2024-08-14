#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

bool validation(char letter) {
    char invalid[50] = "() ,";
    for (int i = 0; i < strlen(invalid); i++) {
        if (letter == invalid[i]) {
            return false;
        }
    }
    return true;
}

int main() {
    char tempStr[80];
    char str[100];
    int maxLetter, i, y;

    FILE *file = fopen("pub.in", "r");
    while (true) {
        // Check if is the end key
        fscanf(file, "%i ", &maxLetter);
        if (maxLetter == 0) break;

        // Read all integer values
        int num[maxLetter];
        for (i = 0; i < maxLetter; i++) {
            fscanf(file, "%i ", &num[i]);
        }

        // Read line
        fscanf(file, " %[^\n]", str);

        // Get boolean operations name and variables names
        for (i = 0, y = 0; i < strlen(str); i++) {
            if (validation(str[i])) {  // If letter is valid, add to tempString
                tempStr[y] = str[i];
                y += 1;
            } else {  // If isn't a valid letter reset tempString
                if (strcmp(tempStr, "\0") != 0) printf("[%s] ", tempStr);
                memset(tempStr, 0, sizeof(tempStr));
                y = 0;
            }
        }
        printf("\n");
    }
    fclose(file);
    return 0;
}