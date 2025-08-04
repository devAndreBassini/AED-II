#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>

#define IS_FIM(str) (strcmp((str), "FIM") == 0)
#define FILE_PATH "../disneyplus.csv"
// #define FILE_PATH "/tmp/disneyplus.csv"

FILE* file;

typedef struct{
    char D[10]; 
    char M[10]; 
    char Y[10]; 
} Date;
typedef struct {
    char show_id[10]; 
    char type[30];  
    char title[300]; 
    char director[300];
    char cast[200];
    char country[200];
    Date date_added; 
    int release_year;
    char rating[30];
    char duration[30];
    char listed_in[300];
    char description[300];
} Movie;
typedef struct {
    char cmd[4];
    char id[10];
    int pos;
} Input;
typedef struct{
    char s[200];
} String;

void printMovie(Movie movie){
    printf("=> ");
    printf("%s", movie.show_id);
    printf(" ## ");
    printf("%s", movie.title);
    printf(" ## ");
    printf("%s", movie.type);
    printf(" ## ");
    printf("%s", movie.director);
    printf(" ## ");
    printf("%s", movie.cast);
    printf(" ## ");
    printf("%s", movie.country);
    printf(" ## ");
    printf("%s %s, %s", movie.date_added.M, movie.date_added.D, movie.date_added.Y);
    printf(" ## ");
    printf("%i", movie.release_year); 
    printf(" ## ");
    printf("%s", movie.rating);
    printf(" ## ");
    printf("%s", movie.duration);
    printf(" ## ");
    printf("%s", movie.listed_in);
    printf(" ##\n");
}

Date blankDate(){
    Date date;
    strcpy(date.D, "");
    strcpy(date.M, "");
    strcpy(date.Y, "");
    return date;
}
Movie blankMovie(){
    Movie movie;
    strcpy(movie.show_id, "NaN");
    strcpy(movie.type, "NaN");
    strcpy(movie.title, "NaN");
    strcpy(movie.director, "NaN");
    strcpy(movie.cast, "NaN");
    strcpy(movie.country, "NaN");
    movie.date_added = blankDate();
    movie.release_year = 0;
    strcpy(movie.rating, "NaN");
    strcpy(movie.duration, "NaN");
    strcpy(movie.listed_in, "NaN");
    strcpy(movie.description, "NaN");
    return movie;
}
Input blankInput(){
    Input input;
    strcpy(input.cmd, " ");
    strcpy(input.id, " ");
    input.pos = 0;
    return input;
}

Date getDate(char *str){
    Date date = blankDate();

    sscanf(str, "\"%s %s %s", date.M, date.D, date.Y);
    date.D[strlen(date.D)-1] = '\0';
    date.Y[strlen(date.Y)-1] = '\0';
    
    return date;
}
Movie parseString(char *str, int index, Movie movie) {
    if(str[0] == '\0') strcpy(str, "NaN");
    switch (index) {
        case 1:
            strcpy(movie.show_id, str);
            break;
        case 2:
            strcpy(movie.type, str);
            break;
        case 3:
            strcpy(movie.title, str);
            break;
        case 4:
            strcpy(movie.director, str);
            break;
        case 5:
            strcpy(movie.cast, str);
            break;
        case 6:
            strcpy(movie.country, str);
            break;
        case 7:
            movie.date_added = getDate(str);
            break;
        case 8:
            movie.release_year = atoi(str);
            break;
        case 9:
            strcpy(movie.rating, str);
            break;
        case 10:
            strcpy(movie.duration, str);
            break;
        case 11:
            strcpy(movie.listed_in, str);
            break;
        case 12:
            strcpy(movie.description, str);
            break;
    }
    return movie;
}
char *sortABC(char *str){
    int elements = 1, len = strlen(str);
    char sortedStr[len];

    if(str[0] == '"'){
        str[len-1] = '\0';
        memmove(str, str + 1, len);
        len = len - 2;
    }

    for(int i = 0; i < len; i++){
        if(str[i] == ',') elements++;
    }
    
    String vec[elements], sorted[elements];
    char tmp[20];
    int index = 0, j = 0;

    for(int i = 0; i < len; i++){
        if(str[i] == ' ' && str[i-1] == ','){
            vec[index].s[j-1] = '\0';
            index++;
            j = 0;
            i++;
        }
        vec[index].s[j] = str[i];
        j++;
    }
    vec[index].s[j] = '\0';

    for (int i = 0; i < elements; i++) {
        strcpy(sorted[i].s, vec[i].s);
    }
    
    for (int i = 0; i < elements - 1; i++) {
        for (int j = 0; j < elements - i - 1; j++) {
            if (strcmp(sorted[j].s, sorted[j + 1].s) > 0) {
                String temp;
                strcpy(temp.s, sorted[j].s);
                strcpy(sorted[j].s, sorted[j + 1].s);
                strcpy(sorted[j + 1].s, temp.s);
            }
        }
    }

    for(int i = 0; i < len; i++){
        for(int j = 0; j < elements; j++){
            for(int c = 0; c < strlen(sorted[j].s); c++){
                str[i] = sorted[j].s[c];
                i++;
            }
            if(j + 1 != elements){
                str[i] = ',';
                str[i+1] = ' ';
                i += 2;
            }
        }
    }
    
    return str;
}

Movie readCSV(char *line){
    Movie thisLine = blankMovie();
    
    bool inQuotes = false;
    
    char tmpStr[900];
    int i, index = 1, count = 0, len = strlen(line);

    for(i = 0; i < len; i++){        
        if(line[i-1] == ',' || line[i+1] == ','){
            if(line[i] == '"') inQuotes = !inQuotes;
        }
   
        if((!inQuotes && line[i] == ',') || i+1 == len){         
            tmpStr[count] = '\0';
            thisLine = parseString(tmpStr, index, thisLine);
            index++;
            count = 0;
        } else {
            tmpStr[count] = line[i];
            count++;
        }
    }

    return thisLine;
}
Movie searchByShow_ID(Movie *list, char *showID, int len){
    for(int i = 0; i < len; i++){
        if(strcmp(list[i].show_id, showID) == 0) return list[i];
    }
    return blankMovie();
}

int main() {
    file = fopen(FILE_PATH, "r");
    if (!file) exit(1);

    char line[500];
    int allMovies_len = 0;
    Movie allMovies[2000];
    
    fgets(line, sizeof(line), file); // Jump 1st line
    for(int i = 0; fgets(line, sizeof(line), file); i++){
        allMovies[i] = readCSV(line);
        allMovies_len++;
    }
    fclose(file);

    for(int i = 0; i < allMovies_len; i++){
        strcpy(allMovies[i].cast, sortABC(allMovies[i].cast));
        strcpy(allMovies[i].director, sortABC(allMovies[i].director));
        strcpy(allMovies[i].country, sortABC(allMovies[i].country));
        strcpy(allMovies[i].listed_in, sortABC(allMovies[i].listed_in));
    }

    
    // Read input and make the list
    strcpy(line, "");
    Movie movie_list[500];
    int pos = 0;

    while(1){
        fgets(line, sizeof(line), stdin);
        line[strcspn(line, "\n")] = '\0';
        if (IS_FIM(line)) break;
        
        movie_list[pos] = blankMovie();
        movie_list[pos] = searchByShow_ID(allMovies, line, allMovies_len);
        pos++;
    }

    // Read the second input and manipulate the list
    int newListSize = 0;
    int inputLen = 0;
    char tmp[1000];
    scanf("%i\n", &inputLen);
    Input input[inputLen];
    
    for(int i = 0; fgets(line, sizeof(line), stdin); i++){
        input[i] = blankInput();
        sscanf(line, "%s", input[i].cmd);
        if(input[i].cmd[1] == '*')
            sscanf(line, "%s %i %s", input[i].cmd, &input[i].pos, input[i].id);
        else
            sscanf(line, "%s %s", input[i].cmd, input[i].id);

        if(input[i].cmd[0] == 'I') newListSize++;
        else if(input[i].cmd[0] == 'R') newListSize--;
    }

    Movie newList[pos];
    int currLen = pos;

    // Dup List
    for (int i = 0; i < pos; i++) {
        newList[i] = movie_list[i];
    }

    for (int i = 0; i < inputLen; i++) {
        char searchFor[50];
        strcpy(searchFor, input[i].id);
        if (strcmp(input[i].cmd, "II") == 0) {
            currLen++;
            for (int j = currLen-1; j > 0; j--) {
                newList[j] = newList[j - 1];
            }            
            newList[0] = searchByShow_ID(allMovies, searchFor, allMovies_len);
        } else if (strcmp(input[i].cmd, "RI") == 0) {
            printf("(R) %s\n", newList[0].title);
            for (int j = 0; j < currLen - 1; j++) {
                newList[j] = newList[j + 1];
            }
            currLen--;
        } else if (strcmp(input[i].cmd, "IF") == 0) {
            currLen++;
            newList[currLen] = searchByShow_ID(allMovies, searchFor, allMovies_len);
        } else if (strcmp(input[i].cmd, "RF") == 0) {
            printf("(R) %s\n", newList[currLen - 1].title);
            currLen--;
        } else if (strcmp(input[i].cmd, "I*") == 0) {
            currLen++;
            for (int j = currLen; j > input[i].pos; j--) {
                newList[j] = newList[j - 1];
            }
            newList[input[i].pos] = searchByShow_ID(allMovies, searchFor, allMovies_len);
        } else if (strcmp(input[i].cmd, "R*") == 0) {
            printf("(R) %s\n", newList[input[i].pos].title);
            for (int j = input[i].pos; j < currLen - 1; j++) {
                newList[j] = newList[j + 1];
            }
            currLen--;
        }
    }


    for(int i = 0; i < currLen; i++){
        printMovie(newList[i]);
    }


    return 0;
}
