#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// Função de troca de elementos
void swap(int* a, int* b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

// QuickSort com o primeiro elemento como pivô
void QuickSortFirstPivot(int array[], int left, int right) {
    if (left < right) {
        int pivot = array[left];
        int i = left;
        int j = right;

        while (i < j) {
            while (i <= right && array[i] <= pivot) i++;
            while (array[j] > pivot) j--;
            if (i < j) {
                swap(&array[i], &array[j]);
            }
        }
        array[left] = array[j];
        array[j] = pivot;

        QuickSortFirstPivot(array, left, j - 1);
        QuickSortFirstPivot(array, j + 1, right);
    }
}

// QuickSort com o último elemento como pivô
void QuickSortLastPivot(int array[], int left, int right) {
    if (left < right) {
        int pivot = array[right];
        int i = left;
        int j = right - 1;

        while (i <= j) {
            while (i <= j && array[i] <= pivot) i++;
            while (j >= i && array[j] > pivot) j--;
            if (i < j) {
                swap(&array[i], &array[j]);
            }
        }
        array[right] = array[i];
        array[i] = pivot;

        QuickSortLastPivot(array, left, i - 1);
        QuickSortLastPivot(array, i + 1, right);
    }
}

// QuickSort com pivô aleatório
void QuickSortRandomPivot(int array[], int left, int right) {
    if (left < right) {
        int randomIndex = left + rand() % (right - left + 1);
        swap(&array[randomIndex], &array[right]);
        int pivot = array[right];

        int i = left;
        int j = right - 1;

        while (i <= j) {
            while (i <= j && array[i] <= pivot) i++;
            while (j >= i && array[j] > pivot) j--;
            if (i < j) {
                swap(&array[i], &array[j]);
            }
        }
        array[right] = array[i];
        array[i] = pivot;

        QuickSortRandomPivot(array, left, i - 1);
        QuickSortRandomPivot(array, i + 1, right);
    }
}

// Função auxiliar para encontrar a mediana de três elementos
int medianOfThree(int array[], int left, int right) {
    int mid = (left + right) / 2;

    if (array[left] > array[mid]) {
        swap(&array[left], &array[mid]);
    }
    if (array[left] > array[right]) {
        swap(&array[left], &array[right]);
    }
    if (array[mid] > array[right]) {
        swap(&array[mid], &array[right]);
    }

    return mid;
}

// QuickSort com mediana de três elementos
void QuickSortMedianOfThree(int array[], int left, int right) {
    if (left < right) {
        int medianIndex = medianOfThree(array, left, right);
        swap(&array[medianIndex], &array[right]);
        int pivot = array[right];

        int i = left;
        int j = right - 1;

        while (i <= j) {
            while (i <= j && array[i] <= pivot) i++;
            while (j >= i && array[j] > pivot) j--;
            if (i < j) {
                swap(&array[i], &array[j]);
            }
        }
        array[right] = array[i];
        array[i] = pivot;

        QuickSortMedianOfThree(array, left, i - 1);
        QuickSortMedianOfThree(array, i + 1, right);
    }
}

// Função para imprimir o array
void printArray(int array[], int size) {
    for (int i = 0; i < size; i++) {
        printf("%d ", array[i]);
    }
    printf("\n");
}

// Função principal para testar as diferentes versões do QuickSort
int main() {
    srand(time(NULL));  // Inicializa o gerador de números aleatórios

    int array1[] = {34, 7, 23, 32, 5, 62, 32, 4};
    int array2[] = {34, 7, 23, 32, 5, 62, 32, 4};
    int array3[] = {34, 7, 23, 32, 5, 62, 32, 4};
    int array4[] = {34, 7, 23, 32, 5, 62, 32, 4};

    int size = sizeof(array1) / sizeof(array1[0]);

    // QuickSort com o primeiro pivô
    printf("Array original: ");
    printArray(array1, size);

    printf("QuickSort com primeiro pivô: ");
    QuickSortFirstPivot(array1, 0, size - 1);
    printArray(array1, size);

    // QuickSort com o último pivô
    printf("\nQuickSort com último pivô: ");
    QuickSortLastPivot(array2, 0, size - 1);
    printArray(array2, size);

    // QuickSort com pivô aleatório
    printf("\nQuickSort com pivô aleatório: ");
    QuickSortRandomPivot(array3, 0, size - 1);
    printArray(array3, size);

    // QuickSort com mediana de três
    printf("\nQuickSort com mediana de três: ");
    QuickSortMedianOfThree(array4, 0, size - 1);
    printArray(array4, size);

    return 0;
}
