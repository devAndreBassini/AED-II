#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

typedef struct {
    int id;
    int generation;
    char name[50];
    char description[255];
    char types[255];
    char abilities[255];
    double weight;
    double height;
    int captureRate;
    bool isLegendary;
    struct tm captureDate;
} Pokemon;

// Função para imprimir um Pokémon
void imprimir(Pokemon p) {
    printf("[#%d -> %s: %s - [%s] - [%s] - %.1fkg - %.1fm - %d%% - %s - %d gen] - %02d/%02d/%04d\n",
           p.id, p.name, p.description, p.types, p.abilities, p.weight, p.height, p.captureRate,
           p.isLegendary ? "true" : "false", p.generation,
           p.captureDate.tm_mday, p.captureDate.tm_mon + 1, p.captureDate.tm_year + 1900);
}

// Função para ler uma linha do arquivo CSV
void ler(Pokemon *p, char *linha) {
    char *token;
    
    // ID
    token = strtok(linha, ",");
    p->id = token ? atoi(token) : 0;

    // Name
    token = strtok(NULL, ",");
    strcpy(p->name, token ? token : "");

    // Description
    token = strtok(NULL, ",");
    strcpy(p->description, token ? token : "");

    // Types
    token = strtok(NULL, ",");
    strcpy(p->types, token ? token : "");

    // Abilities
    token = strtok(NULL, ",");
    strcpy(p->abilities, token ? token : "");

    // Weight
    token = strtok(NULL, ",");
    p->weight = token ? atof(token) : 0.0;

    // Height
    token = strtok(NULL, ",");
    p->height = token ? atof(token) : 0.0;

    // Capture Rate
    token = strtok(NULL, ",");
    p->captureRate = token ? atoi(token) : 0;

    // Is Legendary
    token = strtok(NULL, ",");
    p->isLegendary = token && strcmp(token, "true") == 0;

    // Generation
    token = strtok(NULL, ",");
    p->generation = token ? atoi(token) : 0;

    // Capture Date
    token = strtok(NULL, ",");
    if (token) {
        sscanf(token, "%d/%d/%d", &p->captureDate.tm_mday, &p->captureDate.tm_mon, &p->captureDate.tm_year);
        p->captureDate.tm_mon--; // Ajusta o mês
        p->captureDate.tm_year -= 1900; // Ajusta o ano
    } else {
        p->captureDate.tm_mday = 1;
        p->captureDate.tm_mon = 0;
        p->captureDate.tm_year = 70; // Data padrão 01/01/1970
    }
}

int main() {
    FILE *file = fopen("pokemon.csv", "r");
    if (!file) {
        perror("Erro ao abrir o arquivo");
        return 1;
    }

    Pokemon pokemons[1000];
    int count = 0;
    char linha[1024];

    fgets(linha, sizeof(linha), file); // Pula a primeira linha (cabeçalho)

    while (fgets(linha, sizeof(linha), file)) {
        if (strncmp(linha, "FIM", 3) == 0) break;

        ler(&pokemons[count], linha);
        count++;
    }

    fclose(file);

    for (int i = 0; i < count; i++) {
        imprimir(pokemons[i]);
    }

    return 0;
}
