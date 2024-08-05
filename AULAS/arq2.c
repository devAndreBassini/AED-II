#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main ()
{
    clock_t inicio, fim, T;
    int Tempo, media = 0;
    int c;
    int *i,*j, x = 3, y = 1; // <<<<<<<<<<<<
    i = &x;
    j = &y;


    int k, num1 = 1, num2 = 3;

    T = CLOCKS_PER_SEC;

    for (k = 1; k <= 10; k = k + 1)
    {
        inicio = clock ();

        for (c = 1; c <= 10000000; c = c + 1) *i=*i+*j; //<<<<<<

        fim = clock ();
        Tempo = ((fim - inicio) * 1000 / CLOCKS_PER_SEC);

        printf ("\nTempo: %i s.", Tempo);

        media = media + Tempo;
    }

    printf ("\nTempo gasto media: %i s.", media / 10);

    return 0;
}
