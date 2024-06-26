#include <stdio.h>
#include <string.h>

#define MAX_SIZE 300

// Function responsible to right shift the String
void ShiftR(char *String, int readerAt, int insertAt){ 
  for(int i = 0; i < readerAt - insertAt; i++){
      String[readerAt - i] = String[readerAt - (i + 1)];
  } 
}

// Function responsible to count how many misstyped keys are
int ContadorErros(char *String){
  int erros = 0;
  for(int i = 0; i < strlen(String); i++){
    if(String[i] == '[' || String[i] == ']')
      erros++;
  }
  return erros;
}

int main(){
  char String[MAX_SIZE] = "This_is_a_[Beiju]_text"; 
  // char String[MAX_SIZE] = "[[]][]Happy_Birthday_Tsinghua_University";
  
  // Set 'NewString' values
  int StringSize = strlen(String);
  int erros = ContadorErros(String);
  int NewStringSize = StringSize - erros;
  char NewString[NewStringSize];
  int add = 0;
  

  for(int i = 0; i < StringSize; i++){
    // Check if after a 'HOME' key is a letter 
      if(String[i] == '[' && (String[i + 1] != '[' && String[i + 1] != ']')){
        add = 0;
        // Repeat the insertion and shift while the character is different from an 'END' key
        for(i = i + 1; String[i] != ']'; i++){
          ShiftR(NewString, i, add);
          NewString[add] = String[i];       
          add++;
        }
        // After shifting and inserting the beiju text, set the add variable to the correct position
        add = i - 1;
      } else {
        // Copy the 'String' to the 'NewString' while have the same position
        NewString[add] = String[i];
        add++;
      }
    }  
  // Set the last character to '\0' to prevent memory excess
  NewString[NewStringSize] = '\0';

  printf("%s\n", NewString); // ANSWER
}

