package ordenacao;

// Interface padrão definida no enunciado do projeto.
public interface SortingAlgorithm {
    // Ordena o vetor em ordem crescente.
    void sort(int[] array);

    // Número de comparações.
    long getComparisons();

    // Número de trocas.
    long getSwaps();

    // Tempo de execução.
    long getExecutionTime();

    // Retorna true se o vetor estiver em ordem crescente.
    boolean isSorted(int[] array);
}