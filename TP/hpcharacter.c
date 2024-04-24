#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_CHARS 1000
#define MAX_HPC 500

typedef struct {
    char id[MAX_CHARS];
    char name[MAX_CHARS];
    char house[MAX_CHARS];
    char ancestry[MAX_CHARS];
    char species[MAX_CHARS];
    char patronus[MAX_CHARS];
    char hogwartsStudent[MAX_CHARS];
    char actorName[MAX_CHARS];
    char dateOfBirth[MAX_CHARS];
    char eyeColour[MAX_CHARS];
    char gender[MAX_CHARS];
    char hairColour[MAX_CHARS];
    int yearOfBirth;
    int alive;
    int hogwartsStaff;
    int wizard;
    char alternateNames[MAX_CHARS][MAX_CHARS];
    int alternateNamesCount;
} HPCharacter;

HPCharacter HPC[MAX_HPC];

void imprimir(HPCharacter character) {
    printf("ID: %s\n", character.id);
    printf("Nome: %s\n", character.name);
    printf("Nomes alternativos:\n");
    for (int i = 0; i < character.alternateNamesCount; i++) {
        printf("%s\n", character.alternateNames[i]);
    }
    printf("Casa: %s\n", character.house);
    printf("Ascendência: %s\n", character.ancestry);
    printf("Espécie: %s\n", character.species);
    printf("Patronus: %s\n", character.patronus);
    printf("Professor de Hogwarts: %s\n", character.hogwartsStaff ? "True" : "False");
    printf("Estudante de Hogwarts: %s\n", character.hogwartsStudent);
    printf("Nome do ator: %s\n", character.actorName);
    printf("Vivo: %s\n", character.alive ? "True" : "False");
    printf("Data de nascimento: %s\n", character.dateOfBirth);
    printf("Ano de nascimento: %d\n", character.yearOfBirth);
    printf("Cor dos olhos: %s\n", character.eyeColour);
    printf("Gênero: %s\n", character.gender);
    printf("Cor do cabelo: %s\n", character.hairColour);
    printf("Bruxo: %s\n", character.wizard ? "True" : "False");
}

void read(char* file) {
    FILE *fp;
    char line[MAX_CHARS];
    char *token;
    int k = 0;

    fp = fopen(file, "r");
    if (fp == NULL) {
        perror("Erro ao abrir o arquivo");
        exit(EXIT_FAILURE);
    }

    fgets(line, MAX_CHARS, fp); // Ignora a primeira linha do arquivo

    while (fgets(line, MAX_CHARS, fp) != NULL && k < MAX_HPC) {
        token = strtok(line, ",");
        if (token == NULL) continue; // Verifica se o token é NULL

        strcpy(HPC[k].id, token);
        token = strtok(NULL, ",");
        
        if (token == NULL) continue;

        strcpy(HPC[k].name, token);
        token = strtok(NULL, ",");

        HPC[k].alternateNamesCount = 0;
        while (token && strcmp(token, "['")) {
            strcpy(HPC[k].alternateNames[HPC[k].alternateNamesCount], token);
            HPC[k].alternateNamesCount++;
            token = strtok(NULL, ",");
        }

        token = strtok(NULL, ",");
        strcpy(HPC[k].house, token);

        token = strtok(NULL, ",");
        strcpy(HPC[k].ancestry, token);

        token = strtok(NULL, ",");
        strcpy(HPC[k].species, token);

        token = strtok(NULL, ",");
        strcpy(HPC[k].patronus, token);

        token = strtok(NULL, ",");
        HPC[k].hogwartsStaff = (strcmp(token, "True") == 0) ? 1 : 0;

        token = strtok(NULL, ",");
        strcpy(HPC[k].hogwartsStudent, token);

        token = strtok(NULL, ",");
        strcpy(HPC[k].actorName, token);

        token = strtok(NULL, ",");
        HPC[k].alive = (strcmp(token, "True") == 0) ? 1 : 0;

        token = strtok(NULL, ",");
        strcpy(HPC[k].dateOfBirth, token);

        token = strtok(NULL, ",");
        HPC[k].yearOfBirth = atoi(token);

        token = strtok(NULL, ",");
        strcpy(HPC[k].eyeColour, token);

        token = strtok(NULL, ",");
        strcpy(HPC[k].gender, token);

        token = strtok(NULL, ",");
        strcpy(HPC[k].hairColour, token);

        token = strtok(NULL, ",");
        HPC[k].wizard = (strcmp(token, "True") == 0) ? 1 : 0;

        k++;
    }

    fclose(fp);
}

int main() {
    read("characters.csv");

    // Exibe os personagens lidos do arquivo
    for (int i = 0; i < MAX_HPC && strlen(HPC[i].id) > 0; i++) {
        printf("Personagem %d:\n", i+1);
        imprimir(HPC[i]);
        printf("\n");
    }

    return 0;
}
