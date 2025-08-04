import random
import time
import matplotlib.pyplot as plt
import pandas as pd
import os

# Classe para armazenar estatísticas
class SortStats:
    def __init__(self):
        self.comparacoes = 0
        self.movimentacoes = 0

# Algoritmos de ordenação
def selection_sort(arr, stats):
    n = len(arr)
    for i in range(n):
        min_idx = i
        for j in range(i + 1, n):
            stats.comparacoes += 1
            if arr[j] < arr[min_idx]:
                min_idx = j
        if i != min_idx:
            arr[i], arr[min_idx] = arr[min_idx], arr[i]
            stats.movimentacoes += 3

def insertion_sort(arr, stats):
    for i in range(1, len(arr)):
        chave = arr[i]
        j = i - 1
        while j >= 0:
            stats.comparacoes += 1
            if arr[j] > chave:
                arr[j + 1] = arr[j]
                stats.movimentacoes += 1
                j -= 1
            else:
                break
        arr[j + 1] = chave
        stats.movimentacoes += 1

def bubble_sort(arr, stats):
    n = len(arr)
    for i in range(n):
        for j in range(0, n - i - 1):
            stats.comparacoes += 1
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
                stats.movimentacoes += 3

def quicksort(arr, stats):
    def _quicksort(arr, low, high):
        if low < high:
            pi = partition(arr, low, high)
            _quicksort(arr, low, pi - 1)
            _quicksort(arr, pi + 1, high)

    def partition(arr, low, high):
        pivot = arr[high]
        i = low - 1
        for j in range(low, high):
            stats.comparacoes += 1
            if arr[j] < pivot:
                i += 1
                arr[i], arr[j] = arr[j], arr[i]
                stats.movimentacoes += 3
        arr[i + 1], arr[high] = arr[high], arr[i + 1]
        stats.movimentacoes += 3
        return i + 1

    _quicksort(arr, 0, len(arr) - 1)

# Geração incremental dos gráficos
def gerar_graficos(df, tamanho):
    metricas = ['Tempo (ms)', 'Comparações', 'Movimentações']

    for metrica in metricas:
        plt.figure(figsize=(10, 6))
        for algoritmo in df['Algoritmo'].unique():
            dados = df[df['Algoritmo'] == algoritmo]
            plt.plot(dados['Tamanho'], dados[metrica], marker='o', label=algoritmo)

        plt.title(f'{metrica} por Tamanho de Vetor (até {tamanho})')
        plt.xlabel('Tamanho do vetor')
        plt.ylabel(metrica)
        plt.xscale('log')
        plt.yscale('log')
        plt.grid(True)
        plt.legend()
        nome_arquivo = f'graficos/{metrica.replace(" ", "_").lower()}_ate_{tamanho}.png'
        plt.savefig(nome_arquivo)
        plt.close()

# Execução principal
if __name__ == "__main__":
    os.makedirs("dados", exist_ok=True)
    os.makedirs("graficos", exist_ok=True)

    tamanhos = [100, 1000, 10000, 100000]
    algoritmos = {
        'Selection Sort': selection_sort,
        'Insertion Sort': insertion_sort,
        'Bubble Sort': bubble_sort,
        'Quicksort': quicksort
    }

    resultados = []

    for tamanho in tamanhos:
        base = [random.randint(0, 1000000) for _ in range(tamanho)]
        for nome, algoritmo in algoritmos.items():
            vetor = base.copy()
            stats = SortStats()
            inicio = time.time()
            algoritmo(vetor, stats)
            fim = time.time()
            tempo_ms = (fim - inicio) * 1000
            resultados.append({
                'Algoritmo': nome,
                'Tamanho': tamanho,
                'Tempo (ms)': tempo_ms,
                'Comparações': stats.comparacoes,
                'Movimentações': stats.movimentacoes
            })
            print(f"{nome} - Tamanho: {tamanho} - Tempo: {tempo_ms:.2f} ms")

        # Atualiza gráficos parciais
        df_parcial = pd.DataFrame(resultados)
        gerar_graficos(df_parcial, tamanho)

    # Salvar dados finais
    df_final = pd.DataFrame(resultados)
    df_final.to_csv("dados/resultados_ordenacao.csv", index=False)
