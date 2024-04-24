#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(){
    FILE *file;
    char line[100000];
    char home = '[', end = ']';
    char tempChar;
    int keyError = 0;
    int j = 0, i = 0;

    file = fopen("Warmup.in", "r");
    //while(fgets(line, 100, file)){
        fgets(line, 100, file);
        fgets(line, 100, file);
        line[strlen(line)-1] = '\0';
   
        for(i = 0; i < strlen(line); i++){        
         //   printf("\nA%i", i);
            while(line[i] == '[' || line[i] == ']'){
                i++;
                keyError++;
           //     printf(" +1");
            }
        
            tempChar = line[j];
            line[j] = line[i];
            line[i] = tempChar;
            j++;
        }

        line[strlen(line)-keyError] = '\0';
      
        printf("%s\n", line);
   // }
    fclose(file);
    return 0;
}