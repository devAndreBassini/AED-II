#include <stdio.h>
#include <string.h>

const int MAX_LINE_SIZE = 300;

// Function responsible to right shift the String
void ShiftR(char *String, int CurrentPos){
  for(int i = 0; i < CurrentPos; i++){
    String[CurrentPos - i] = String[CurrentPos - (i + 1)];
  }
}

int main(){
  // Reading Input
  FILE *fromFile;
  char line[MAX_LINE_SIZE];
  fromFile = fopen("Warmup.in", "r");
  fgets(line, MAX_LINE_SIZE, fromFile);
  fclose(fromFile);

  // Set 'String' size
  int LineSize = strlen(line);
  char String[LineSize];
  
  // Copy the 'line' string to 'String' string
  strcpy(String, line);

  // Count how many misstyped keys were typed
  int ERROS = 0;
  for(int i = 0; i < 10; i++){
    if(String[i] == '[' || String[i] == ']')
      ERROS =+ 1;
  }

  // Set 'NewString' size
  int NewLineSize = LineSize - ERROS;
  char NewString[NewLineSize];
  
  // Insert position control variable  
  int InsertAt = 0;
  
  // Loop responsible to fill the 'NewString'
  for(int i = 0; i < NewLineSize; i++){ // <<<<<<<<< ERRO
    /* Check if the current  position is an 'HOME' key and  if the next position are different from the broken keys */ 
    if(String[i] == '[' && String[i + 1] != ']' && String[i + 1] != '['){
      // Reset the control variable after reordering the 'String'
      InsertAt = 0;
      // Rearranging string
      while(String[i + 1] != ']'){
        i++; // Skip the broken key character
        ShiftR(NewString, i);
        NewString[InsertAt] = String[i];
        InsertAt++;
      }
    
    // Copy normaly the input 'String' to the 'NewString'
    } else {
       NewString[InsertAt] = String[i];
       InsertAt++;
    }
    
    // Check if the next character is the 'END' key and set the 'InsertAt' to the right value
    if(String[i + 1] == ']'){
      i++;
      InsertAt = (i + 1) - ERROS;
    }    
  }
  // Print 'NewString' on screen
  printf("%s", NewString);
}