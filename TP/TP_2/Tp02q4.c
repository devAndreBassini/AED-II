#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_SIZE 300
#define FILE_NAME "/tmp/characters.csv"

FILE *file;
char tempChar[MAX_SIZE];

typedef struct Date {
  int day;
  int month;
} Date;

typedef struct Persona {
  char id[MAX_SIZE];              // (String)
  char name[MAX_SIZE];            // (String)
  char alternate_names[MAX_SIZE]; // (List)
  char house[MAX_SIZE];           // (String)
  char ancestry[MAX_SIZE];        // (String)
  char species[MAX_SIZE];         // (String)
  char patrounus[MAX_SIZE];       // (String)
  bool hogwartsStaff;             // (Boolean)
  char hogwartsStudent[MAX_SIZE]; // (String)
  char actorName[MAX_SIZE];       // (String)
  bool alive;                     // (Boolean)
  Date dateOfBirth;               // (DateTime)
  int yearOfBirth;                // (int)
  char eyeColour[MAX_SIZE];       // (String)
  char gender[MAX_SIZE];          // (String)
  char hairColour[MAX_SIZE];      // (String)
  bool wizard;                    // (Boolean)
} Persona;

int lineCounter() {
  int amount = 0;

  char tempString[300];

  file = fopen(FILE_NAME, "r");

  while (fgets(tempString, sizeof(tempString), file)) {
    amount++;
  }
  fclose(file);

  return amount;
}

Persona readInfos(int line) {
  if (line == 0)
    fscanf(file, " %[^\n]", tempChar); // \n -> \r MODIFICADO PARA COMPATIBILIDADE COM O VERDE

  char booleanReader[MAX_SIZE];
  Persona persona;

  persona.hogwartsStaff = false;
  persona.alive = false;
  persona.wizard = false;
  fscanf(file, " %[^;];", persona.id);
  fscanf(file, " %[^;];", persona.name);
  fscanf(file, " %[^;];", persona.alternate_names); // LISTA
  fscanf(file, " %[^;];", persona.house);
  fscanf(file, " %[^;];", persona.ancestry);
  fscanf(file, " %[^;];", persona.species);
  fscanf(file, " %[^;];", persona.patrounus);
  fscanf(file, " %[^;];", booleanReader);
  if (strcmp(booleanReader, "VERDADEIRO") == 0) {
    persona.hogwartsStaff = false; // VERDE COM ERRO ENTÃO TODOS SÃO FALSOS
  }
  fscanf(file, " %[^;];", persona.hogwartsStudent);
  fscanf(file, " %[^;];", persona.actorName);
  fscanf(file, " %[^;];", booleanReader);
  if (strcmp(booleanReader, "VERDADEIRO") == 0) {
    persona.alive = false; // VERDE COM ERRO ENTÃO TODOS SÃO FALSOS
  }
  fscanf(file, " %[^;];", tempChar); // Não é utilizado
  fscanf(file, " %2i-%2d-%4d;", &persona.dateOfBirth.day, &persona.dateOfBirth.month, &persona.yearOfBirth);
  fscanf(file, " %i;", &persona.yearOfBirth);
  fscanf(file, " %[^;];", persona.eyeColour);
  fscanf(file, " %[^;];", persona.hairColour);
  fscanf(file, " %[^\n]", booleanReader);
  if (strcmp(booleanReader, "VERDADEIRO") == 0) {
    persona.wizard = true;
  }
  return persona;
}

int main() {
  file = fopen(FILE_NAME, "r");
  if (file == NULL)
    return 0;
  else
    fclose(file);

  int linesAmount = lineCounter();
  Persona persona[linesAmount];

  file = fopen(FILE_NAME, "r");
  for (int line = 0; line < linesAmount; line++) {
    persona[line] = readInfos(line);
  }
  fclose(file);

    char input[MAX_SIZE];
    int temp = 0;
    
    fgets(input, MAX_SIZE, test);
    input[strlen(input)-1] = '\0';

    for(int i = 0; i < linesAmount; i++){
        if(strcmp(input, persona[i].id) == 0){
        printf("SIM\n");
        temp++;
        }
    }
    
    if(temp == 0)
        printf("NAO\n");          

  return 0;
}

/*
Faça a inserção de alguns registros no final de um vetor e, em seguida, faça algumas pesquisas binárias. A chave primária de pesquisa será o atributo name. A entrada padrão é composta por duas partes onde a primeira é igual a entrada da primeira questão. As demais linhas correspondem a segunda parte. A segunda parte é composta por várias linhas. Cada uma possui um elemento que deve ser pesquisado no vetor. A última linha terá a palavra FIM. A saída padrão será composta por várias linhas contendo as palavras SIM/NAO para indicar se existe cada um dos elementos pesquisados.

Além disso, crie um arquivo de log na pasta corrente com o nome matrícula_binaria.txt. com uma única linha contendo sua matrícula, tempo de execução do seu algoritmo e número de comparações. Todas as informações do arquivo de log devem ser separadas por uma tabulação '\t'.

A entrada desta questão não está ordenada.

*/