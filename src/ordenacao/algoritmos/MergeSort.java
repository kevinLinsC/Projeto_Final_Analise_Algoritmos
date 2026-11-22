package ordenacao.algoritmos;

import ordenacao.AbstractSort;

// Merge Sort: divide o vetor ao meio até sobrar um elemento em cada parte
// e depois junta as metades já ordenadas.
public class MergeSort extends AbstractSort {
    private int[] aux;

    @Override
    public String getName() {
        return "Merge Sort";
    }

    @Override
    public String getComplexity() {
        return "O(n log n)";
    }

    @Override
    protected void doSort(int[] array) {
        if (array.length < 2) {
            return;
        }
        // Vetor auxiliar alocado uma única vez e reaproveitado em todas as intercalações.
        aux = new int[array.length];
        auxMemoryBytes = (long) array.length * Integer.BYTES;
        mergeSort(array, 0, array.length - 1, 1);
        aux = null;
    }

    private void mergeSort(int[] array, int inicio, int fim, int profundidade) {
        if (profundidade > maxRecursionDepth) {
            maxRecursionDepth = profundidade;
        }
        // Caso base: um elemento (ou nenhum) já está ordenado.
        if (inicio >= fim) {
            return;
        }
        // Divide: ordena cada metade recursivamente. Conquista: intercala as duas.
        int meio = inicio + (fim - inicio) / 2;
        mergeSort(array, inicio, meio, profundidade + 1);
        mergeSort(array, meio + 1, fim, profundidade + 1);
        merge(array, inicio, meio, fim);
    }

    private void merge(int[] array, int inicio, int meio, int fim) {
        // Copia o trecho para aux e reescreve no vetor original já intercalado.
        System.arraycopy(array, inicio, aux, inicio, fim - inicio + 1);

        int i = inicio;     // Percorre a metade esquerda.
        int j = meio + 1;   // Percorre a metade direita.
        int k = inicio;     // Posição de escrita no vetor original.

        // Pega sempre o menor entre os primeiros de cada metade.
        while (i <= meio && j <= fim) {
            if (compare(aux[i], aux[j]) <= 0) {
                array[k++] = aux[i++];
            } else {
                array[k++] = aux[j++];
            }
            move();
        }
        // Copia o que sobrou da metade que não terminou.
        while (i <= meio) {
            array[k++] = aux[i++];
            move();
        }
        while (j <= fim) {
            array[k++] = aux[j++];
            move();
        }
    }
}