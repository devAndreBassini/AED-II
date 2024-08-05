#include <stdlib.h>
#include <sttdio.h>

int main() {
    int x;
    do {
        scanf("%i", &x);
        if(x == 0){
            continue;
        } 
        if (x % 2 == 0) {
            printf("P\n");
        } else {
            printf("I\n");
        }
    } while (x != 0);
}