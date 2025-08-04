#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define FIELD_SIZE 1024
#define FIELDS_QTY 12

//#define fprintf //

typedef struct {
    char* show_id;
    char* type;
    char* title;
    char* director; 
    char** cast; 
    char* country; 
    char* date_added;
    int release_year;
    char* rating;
    char* duration;
    char** listed_in;
    char* description;
} Movie;

Movie* allMovies = NULL;
int size = 0, load = 0;

void printMovie(Movie movie){
    printf("%s\n", movie.show_id);
    printf("%s\n", movie.type);
    printf("%s\n", movie.title);
    printf("%s\n", movie.director);
    for(int i = 0; movie.cast != NULL; i++){
        printf("%s\n", movie.cast[i]);
    }
    printf("%s\n", movie.country);
    printf("%s\n", movie.date_added);
    printf("%i\n", movie.release_year);
    printf("%s\n", movie.rating);
    printf("%s\n", movie.duration);
    for(int i = 0; movie.listed_in != NULL; i++){
        printf("%s\n", movie.listed_in[i]);
    }
    printf("%s\n", movie.description);
    
}

void add_movie(Movie m) {
    if (size >= load) {
        load = (load == 0) ? 10 : load * 2;
        allMovies = realloc(allMovies, load * sizeof(Movie));
    }
    allMovies[size++] = m;
}


char** split(char* str, const char* sep) {
    char* token = strtok(str, sep);
    char** res = NULL;
    int count = 0;

    while (token != NULL) {
        res = realloc(res, (count + 1) * sizeof(char*));
        res[count++] = strdup(token);
        token = strtok(NULL, sep);
    }
    return res;
}


void clearField(char* field) {
    size_t len = strlen(field);
    if (field[0] == '"') memmove(field, field + 1, len--);
    if (field[len - 1] == '"') field[len - 1] = '\0';
    if (field[len - 1] == '\n') field[len - 1] = '\0';
}


void readCSV() {
    const char* paths[] = {"/tmp/disneyplus.csv", "../disneyplus.csv"};
 
    FILE* file = NULL;

    for (int i = 0; i < 2; i++) {
        file = fopen(paths[i], "r");
        if (file == NULL) {
            fprintf(stderr, "Falha ao abrir %s\n", paths[i]);
        }
    }

    if (file == NULL) {
        fprintf(stderr, "Nenhum arquivo CSV encontrado.\n");
        return;
    }

    char line[8192];
    fgets(line, sizeof(line), file); // pula cabeçalho

    
    while (fgets(line, sizeof(line), file)) {
        char* fields[FIELDS_QTY];
        char* token = strtok(line, ",");
        int i = 0;
        
        printf("%s\n", line);
        while (token != NULL && i < FIELDS_QTY) {
            fields[i++] = token;
            token = strtok(NULL, ",");
        }

        for (int j = 0; j < FIELDS_QTY; j++) clearField(fields[j]);
        
        Movie m;
        m.show_id = strdup(fields[0]);
        m.type = strdup(fields[1]);
        m.title = strdup(fields[2]);
        m.director = strdup(fields[3]);
        m.cast = split(fields[4], ", "); // Aspas
        m.country = strdup(fields[5]); 
        m.date_added = strdup(fields[6]);
        m.release_year = atoi(fields[7]);
        m.rating = strdup(fields[8]);
        m.duration = strdup(fields[9]);
        m.listed_in = split(fields[10], ", "); 
        m.description = strdup(fields[11]);
        add_movie(m);
        return;
    }
    printMovie(allMovies[0]);

    fclose(file);
   
}



int main(){
    readCSV();
    return 0;
}

