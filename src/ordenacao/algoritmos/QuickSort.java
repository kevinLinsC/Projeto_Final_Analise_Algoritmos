package ordenacao.algoritmos;

import ordenacao.AbstractSort;

// Quick Sort: escolhe um pivô, separa os menores à esquerda e os maiores
// à direita, e repete o processo em cada lado.
public class QuickSort extends AbstractSort {
    @Override
    public String getName() {
        return "Quick Sort";
    }

    @Override
    public String getComplexity() {
        return "O(n log n)";
    }

    @Override
    protected void doSort(int[] array) {
        quickSort(array, 0, array.length - 1, 1);
    }

    private void quickSort(int[] array, int inicio, int fim, int profundidade) {
        while (inicio < fim) {
            if (profundidade > maxRecursionDepth) {
                maxRecursionDepth = profundidade;
            }
            int p = particionar(array, inicio, fim);
            // Recursão na parte menor, laço na maior: limita a pilha a O(log n).
            if (p - inicio < fim - p) {
                quickSort(array, inicio, p, profundidade + 1);
                inicio = p + 1;
            } else {
                quickSort(array, p + 1, fim, profundidade + 1);
                fim = p;
            }
        }
    }

    // Separa o trecho em duas partes: menores ou iguais ao pivô à esquerda, maiores ou iguais à direita.
    // Usa dois índices que vêm das pontas em direção ao meio. Retorna o índice onde o trecho foi dividido.
    private int particionar(int[] array, int inicio, int fim) {
        int meio = inicio + (fim - inicio) / 2;

        // Ordena array[inicio], array[meio], array[fim] -> a mediana fica no meio.
        if (compare(array[meio], array[inicio]) < 0) swap(array, meio, inicio);
        if (compare(array[fim], array[inicio]) < 0) swap(array, fim, inicio);
        if (compare(array[fim], array[meio]) < 0) swap(array, fim, meio);

        // Mediana de três evita o pior caso O(n^2) em vetores ordenados ou invertidos.
        int pivo = array[meio];
        int i = inicio - 1;
        int j = fim + 1;

        while (true) {
            // i avança até achar um elemento >= pivô, j recua até achar um <= pivô.
            do {
                i++;
            } while (compare(array[i], pivo) < 0);
            do {
                j--;
            } while (compare(array[j], pivo) > 0);
            // Os índices se cruzaram: partição concluída.
            if (i >= j) {
                return j;
            }
            // Os dois estão do lado errado: troca.
            swap(array, i, j);
        }
    }
}