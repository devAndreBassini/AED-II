#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(){
    FILE *file;
    char line[100000];
    char letter;

    file = fopen("Warmup.in", "r");
    fgets(line, 100, file);
  //  while(fgets(line, 100, file)) {  }

    fclose(file);

    for(int i = 0; i < strlen(line); i++){
   //     printf("[%i]-%c\n", i, line[i]);
    }


    printf("FIRST: %s\n", line);
    for(int i = 0; i < strlen(line); i++){
        printf("[%i]LINE: %s\n", i, line);
        if(line[i] == '['){
            i++;
            for(int j = 0; line[i] != ']'; j++){
                char tempChar;
                tempChar = line[j];
                line[j] = line[i];
                line[i] = tempChar;
                i++;
                printf("[%i]SWAP[%i]: %s\n", i, j, line);
            }
        }
    }
    
    printf("%s\n", line);
    return 0;
    
    


}