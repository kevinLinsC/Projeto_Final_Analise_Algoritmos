package ordenacao.algoritmos;

import ordenacao.AbstractSort;

// Bubble Sort: compara pares vizinhos e troca os que estão fora de ordem.
// A cada passada, o maior elemento restante sobe para o fim do vetor.
public class BubbleSort extends AbstractSort {
    @Override
    public String getName() {
        return "Bubble Sort";
    }

    @Override
    public String getComplexity() {
        return "O(n^2)";
    }

    @Override
    protected void doSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            boolean trocou = false;
            // Os últimos i elementos já estão na posição final.
            for (int j = 0; j < n - 1 - i; j++) {
                // Se o da esquerda é maior, troca com o vizinho.
                if (compare(array[j], array[j + 1]) > 0) {
                    swap(array, j, j + 1);
                    trocou = true;
                }
            }
            if (!trocou) {
                break; // nenhuma troca = já ordenado.
            }
        }
    }
}