#include <stdio.h>
#include <stdlib.h>

int somador(int value, int sum){
    if(value > 0)
        return somador(value / 10, sum + (value%10));
    else
        return sum;    
}

int main(){
    while(1){
        int value = 0;
        
        scanf("%i", &value);
        
        if(value == 0) break;

        printf("%i\n", somador(value, 0));
    }
    return 0;
}