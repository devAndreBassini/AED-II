#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {
    char str1[100], str2[100], str3[100];
    int size1, size2, size3, pos1 = 0, pos2 = 0;
    int aux = 0;
    
    
    int in = 0;
    while (1) {
        in++;
        if (fscanf(stdin, " %[^ ]", str1) == EOF) break;
        if (fscanf(stdin, " %[^\n]", str2) == EOF) break;

        pos1 = 0;
        pos2 = 0;

        size1 = strlen(str1);
        size2 = strlen(str2);
        size3 = size1 + size2;
        str3[size3] = '\0';

        if (size1 < size2)
            aux = 1;
        else
            aux = 0;

        for (int i = 0; i < size3; i++) {
            if (i < size3 - abs(size1 - size2)) {
                if (i % 2 == 0)
                    str3[i] = str1[pos1++];
                else
                    str3[i] = str2[pos2++];
            } else {
                if (aux == 0)
                    str3[i] = str1[pos1++];
                else
                    str3[i] = str2[pos2++];
            }
        }

        for (int i = 0, j = 0; i < size3; i++) {
            if(str3[i] == 13) j++;
            str3[i] = str3[j];
            if(j < size3) j++;
        }
        str3[size3] = '\0';
        printf("%s\n", str3);
    }
    

    return 0;
}