package ordenacao.algoritmos;

import ordenacao.AbstractSort;

// Insertion Sort: mantém o início do vetor ordenado e insere cada novo
// elemento na posição correta dessa parte, como ao organizar cartas na mão.
public class InsertionSort extends AbstractSort {
    @Override
    public String getName() {
        return "Insertion Sort";
    }

    @Override
    public String getComplexity() {
        return "O(n^2)";
    }

    @Override
    protected void doSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            // array[0..i-1] já está ordenado; chave é o elemento a inserir.
            int chave = array[i];
            int j = i - 1;
            // Desloca para a direita os elementos maiores que a chave.
            while (j >= 0 && compare(array[j], chave) > 0) {
                array[j + 1] = array[j];
                move();
                j--;
            }
            // Coloca a chave no espaço aberto (só se ela saiu do lugar).
            if (j + 1 != i) {
                array[j + 1] = chave;
                move();
            }
        }
    }
}