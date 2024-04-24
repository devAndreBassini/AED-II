#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(){
    FILE *file;
    char line[100000];
    char word[100000];
    char newLine[100000];
    int keyErrors = 0;
    int newSize = 0;
    int i = 0, j = 0, z = 0, lenght = 0;

    file = fopen("Warmup.in", "r");
  //  while(fgets(line, 100, file)) {
        fgets(line, 100, file);
     //   fgets(line, 100, file);
        printf("%s", line);
        
        while(i < strlen(line)){
            if(line[i] == '[' && line[i] == ']'){
                keyErrors++;
            }


            if(line[i] == '[' && line[i+1] != ']'){
                while(line[i+1] != ']'){
                    word[j] = line[i+1];
                    j++;
                    i++;
                }
            }
            i++;
        }

            i = 0;
            j = 0;
            while(i < strlen(word)){
                newLine[j] = word[i];
                j++;
                i++;
            }

            i = strlen(newLine);
            j = 0;
            while(lenght < strlen(line)){
                if(line[j] == '['){
                    while(line[j] != ']'){
                        j++;
                    }
                    j++;
                }
                newLine[i] = line[j];
                i++;
                j++;
                lenght++;
            }

            printf("L: %s", line);
            printf("W: %s\n", word);
            printf("NL: %s\n", newLine);
                
    /*

          for(i = 0; i < strlen(line); i++){
            if(line[i] != '[' && line[i] != ']'){
                word[j] = line[i];
                j++;
            } else {
                if(line[i] == '['){
                    newLine[z] = line[i+1]
                keyErrors++;
            }
        }
            printf("%s\n", word);
                


                word[j] = line[i];
                while(messedKey){
                    if(line[i] == ']'){
                        messedKey = false;
                        keyErrors++;
                    }
                    j++;
                    i++;
                } 
            }
        }
        printf("%i", keyErrors);

        j = 0;
        for(i = 0; i < strlen(line) - keyErrors; i++){
            newLine[i] = word[i];
            if(i >= strlen(word)){
                newLine[i] = line[j];
                j++;
            }
            if(line[j] == '[' || line[j] == ']')
                j += strlen(word) + keyErrors;
        }

        printf("%s\n", newLine);*/
   // }
    fclose(file);
    return 0;
}