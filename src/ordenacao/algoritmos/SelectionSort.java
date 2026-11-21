package ordenacao.algoritmos;

import ordenacao.AbstractSort;

// Selection Sort: a cada passada, encontra o menor elemento da parte
// não ordenada e o coloca na próxima posição do início do vetor.
public class SelectionSort extends AbstractSort {
    @Override
    public String getName() {
        return "Selection Sort";
    }

    @Override
    public String getComplexity() {
        return "O(n^2)";
    }

    @Override
    protected void doSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int indiceMenor = i;
            // Procura o menor elemento em array[i..n-1].
            for (int j = i + 1; j < n; j++) {
                if (compare(array[j], array[indiceMenor]) < 0) {
                    indiceMenor = j;
                }
            }
            // Uma única troca por passada: leva o menor para a posição i.
            if (indiceMenor != i) {
                swap(array, i, indiceMenor);
            }
        }
    }
}