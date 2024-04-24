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

int headerItems(char separator) {
  int amount = 1;
  file = fopen(FILE_NAME, "r");

  char letter = '0';
  while ((letter = fgetc(file)) != '\n') {
    if (letter == separator)
      amount++;
  }
  fclose(file);

  return amount;
}

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

char *readSpecifcLine(int lineNumber) {
  char LineReader[300];
  file = fopen(FILE_NAME, "r");
  for (int i = 0; i < lineNumber; i++) {
    fscanf(file, " %[^\n]", LineReader);
  }
  fclose(file);

  char *result = strdup(LineReader);

  return result;
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

void printOutput(Persona persona) {
  printf("%s", persona.id);
  printf(" ## ");
  printf("%s", persona.name);
  printf(" ## ");
  printf("%s", persona.alternate_names); // LISTA
  printf(" ## ");
  printf("%s", persona.house);
  printf(" ## ");
  printf("%s", persona.ancestry);
  printf(" ## ");
  printf("%s", persona.species);
  printf(" ## ");
  printf("%s", persona.patrounus);
  printf(" ## ");
  printf(persona.hogwartsStaff ? "true" : "false"); // STAFF
  printf(" ## ");
  printf("%s", persona.hogwartsStudent);
  printf(" ## ");
  printf("%s", persona.actorName);
  printf(" ## ");
  printf(persona.alive ? "true" : "false"); // ALIVE
  printf("%i-%i-%i", persona.dateOfBirth.day, persona.dateOfBirth.month, persona.yearOfBirth);
  printf(" ## ");
  printf("%i", persona.yearOfBirth);
  printf(" ## ");
  printf("%s", persona.eyeColour);
  printf(" ## ");
  printf("%s", persona.hairColour);
  printf(" ## ");
  printf(persona.wizard ? "true" : "false"); // WIZARD
  printf("\n");
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

  
  // Function to read an Specific line
  int lineNumber = 11;
  printf("%s\n", readSpecifcLine(lineNumber));

  // Function to count Header items quantity
  char separator; // Separator between header items
  int headerAmount = headerItems(separator = ';');

  
  /*
  for (int i = 0; i < linesAmount; i++) {
    printOutput(persona[i]);
  }
*/
  return 0;
}
