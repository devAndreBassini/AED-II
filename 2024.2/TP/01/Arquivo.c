#include <stdio.h>
#include <stdlib.h>

FILE *file;
char FILE_NAME[80] = "arquivo.txt";

void openFile(const char *type){
    file = fopen(FILE_NAME, type);
}

void resetFile(){
    openFile("w");
    fclose(file);
}

void setValues(int n){
    int x;
    openFile("a");
    for(int i = 0; i < n; i++){
        fprintf(file, "%i\n", i);
        // scanf("%i", &x);
        // fprintf(file, "%i;", x);
    }
    fclose(file);
}

void getValues(int n){
    int x[n];
    openFile("r");

    for(int i = n-1; i >= 0; i--){
        fscanf(file, "%i", &x[i]);
    }
    fclose(file);
    
    for(int i = 0; i < n; i++){
        printf("%i\n", x[i]);
    }
}

int main(){
    int n;

    scanf("%i", &n);
    
    resetFile();
    setValues(n);
    getValues(n);

    return 0;
}