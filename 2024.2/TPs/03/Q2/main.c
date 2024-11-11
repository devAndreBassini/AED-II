#include <ctype.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// #define fprintf //

typedef struct{
    char column[80];
} Column;

// POKEMON
typedef struct {
    int id;
    int generation;
    char name[50];
    char description[80];
    char type1[80];
    char type2[80];
    char abilities[255];
    char ability[800];
    double weight;
    double height;
    int captureRate;
    bool isLegendary;
    char captureDate[800];
} Pokemon;

// INPUT
typedef struct {
    int id;
    char command[2];
} Input;

// GET INPUT
int getID(char *str) {
    for (int i = 0; i < strlen(str); i++) {
        str[i] = toupper(str[i]);
    }

    if (strcmp(str, "II") == 0)
        return 1;
    else if (strcmp(str, "I*") == 0)
        return 2;
    else if (strcmp(str, "IF") == 0)
        return 3;
    else if (strcmp(str, "RI") == 0)
        return 4;
    else if (strcmp(str, "R*") == 0)
        return 5;
    else if (strcmp(str, "RF") == 0)
        return 6;
    else
        return -1;
}

// EXCLUDED CHARS
bool isSymbol(char symbol) {
    if (symbol == '"')
        return true;
    else if (symbol == '[')
        return true;
    else if (symbol == ']')
        return true;
    else if (symbol == ',')
        return true;
    else
        return false;
}

// STRING TO POKEMON
Pokemon stringToPokemon(char str[], int column) {
    Pokemon p;
    char tmp[10];
    switch (column) {
        case 0:
            p.id = atoi(str);
            break;
        case 1:
            p.generation = atoi(str);
            break;
        case 2:
            strcpy(p.name, str);
            break;
        case 3:
            strcpy(p.description, str);
            break;
        case 4:
            strcpy(p.type1, str);
            break;
        case 5:
            strcpy(p.type2, str);
            break;
        case 6:
            strcpy(p.abilities, str);
            break;
        case 7:
            p.weight = atof(str);
            break;
        case 8:
            p.height = atof(str);
            break;
        case 9:
            p.captureRate = atoi(str);
            break;
        case 10:
            if (strcmp(str, "0"))
                p.isLegendary = false;
            else
                p.isLegendary = true;
            break;
        case 11:
            strcpy(p.captureDate, str);
            break;
    }
    return p;
}

// READ CSV
int readCSV(Pokemon *pokemons, int max_pokemons) {
    FILE *file = fopen("/tmp/pokemon.csv", "r");
    if (file == NULL) {
        file = fopen("../pokemon.csv", "r");
    }
    if (file == NULL) {
        exit(1);
    }

    char line[1024];
    int count = 0, cont_abilities = 0, columns = 0;

    fgets(line, sizeof(line), file);  // Skip 1st line

    while (fgets(line, sizeof(line), file) && count < max_pokemons) {
        Column tmpStr[80];

        Pokemon p = {0};
        bool open = false;

        for (int i = 0, j = 0; i < strlen(line); i++) {
            while(isSymbol(line[i])) {
                if(line[i] == '[') open = true;
                if(line[i] == ']') open = false;
                if(line[i] == ',' && !open){
                    if(line[i+1] == ' ') i++;
                    columns++;
                    j = 0;
                }
                i++;
            }

            if(line[i] == '\n'){
                for(int i = 0; i <= columns; i++) {
                    p = stringToPokemon(tmpStr[i].column,i);
                }
                columns = 0;
            }    
            tmpStr[columns].column[j] = line[i];
            j++;
        }
        
        pokemons[count++] = p;
        
    }
    fclose(file);
    return count;
}

Pokemon getPokemon(int id) {
    Pokemon thisPokemon;

    return thisPokemon;
}
void inserirInicio(Pokemon pokemon) {}
void inserir(Pokemon pokemon, int posicao) {}
void removerPokemon(int posicao) {}
void removerFim() {}

int main() {
    char tmp[100] = " ";

    int list[100], aux = 0;
    for (int i = 0; strcmp(tmp, "FIM") != 0; i++) {
        scanf("%s", tmp);
        list[i] = atoi(tmp);
        aux = i;
    }

    Pokemon pokemon[900];
    int totalPokemons = readCSV(pokemon, 900);

    for (int i = 0; i < aux; i++) {
        for (int j = 0; j < totalPokemons; j++) {
            if (list[i] == pokemon[j].id) {
                printf("[%i] ", i);
                printf("[#%i -> ", pokemon[j].id);
                printf("%s: ", pokemon[j].name);
                printf("%s - ", pokemon[j].description);
                printf("[%s ", pokemon[j].type1);
                printf("%s] - ", pokemon[j].type2);
                printf("[%s] - ", pokemon[j].abilities);
                printf("%.1fkg - ", pokemon[j].weight);
                printf("%.1fm - ", pokemon[j].height);
                printf("%i - ", pokemon[j].captureRate);
                pokemon[j].captureRate ? printf("true - ") : printf("false - ");
                printf("%i gen] - ", pokemon[j].generation);
                printf("%s\n", pokemon[j].captureDate);
            }
        }
    }

    return 0;
}