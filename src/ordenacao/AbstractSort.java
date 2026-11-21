package ordenacao;

// Classe base que implementa a parte comum da interface SortingAlgorithm:
// zera os contadores, mede o tempo com System.nanoTime() e valida a ordenação.

public abstract class AbstractSort implements SortingAlgorithm {
    protected long comparisons;
    protected long swaps;
    protected long executionTime;
    // Memória auxiliar alocada pelo algoritmo, em bytes.
    protected long auxMemoryBytes;
    // Profundidade máxima de recursão atingida.
    protected int maxRecursionDepth;

    // Nome do algoritmo, usado nos relatórios e no CSV.
    public abstract String getName();

    // Complexidade teórica no caso médio, usada apenas para exibição.
    public abstract String getComplexity();

    // Implementação do algoritmo propriamente dito.
    protected abstract void doSort(int[] array);

    @Override
    public final void sort(int[] array) {
        comparisons = 0;
        swaps = 0;
        auxMemoryBytes = 0;
        maxRecursionDepth = 0;

        long inicio = System.nanoTime();
        doSort(array);
        executionTime = System.nanoTime() - inicio;
    }

    // Compara dois valores e contabiliza a comparação.
    protected int compare(int a, int b) {
        comparisons++;
        return Integer.compare(a, b);
    }

    // Troca duas posições do vetor e contabiliza a troca.
    protected void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
        swaps++;
    }

    // Contabiliza um movimento que não é uma troca completa, por exemplo, deslocamento no Insertion Sort.
    protected void move() {
        swaps++;
    }

    @Override
    public long getComparisons() {
        return comparisons;
    }

    @Override
    public long getSwaps() {
        return swaps;
    }

    @Override
    public long getExecutionTime() {
        return executionTime;
    }

    public long getAuxMemoryBytes() {
        return auxMemoryBytes;
    }

    public int getMaxRecursionDepth() {
        return maxRecursionDepth;
    }

    @Override
    public boolean isSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) {
                return false;
            }
        }
        return true;
    }
}