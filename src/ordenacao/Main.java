package ordenacao;

import java.util.List;

import ordenacao.algoritmos.BubbleSort;
import ordenacao.algoritmos.InsertionSort;
import ordenacao.algoritmos.MergeSort;
import ordenacao.algoritmos.QuickSort;
import ordenacao.algoritmos.SelectionSort;
import ordenacao.util.DataGenerator;
import ordenacao.util.DataGenerator.TipoDado;

// Programa principal: executa todos os algoritmos sobre as mesmas entradas e mostra os resultados no console.
// 5 execuções por combinação; a 1ª é descartada (warm-up) e a média é calculada sobre as 4 restantes.

public final class Main {
    private static final int[] TAMANHOS = {100, 1000, 5000, 10000, 50000, 100000};
    private static final int LIMITE_QUADRATICO = 50000;
    private static final int EXECUCOES = 5;
    private static final int WARMUP = 1;

    public static void main(String[] args) {
        List<AbstractSort> algoritmos = List.of(
                new BubbleSort(), new InsertionSort(), new SelectionSort(),
                new MergeSort(), new QuickSort());

        imprimirAmbiente();

        for (AbstractSort alg : algoritmos) {
            boolean quadratico = alg.getComplexity().contains("n^2");
            System.out.println("\n=== " + alg.getName() + " (" + alg.getComplexity() + ") ===");
            System.out.printf("  %-10s %-9s %12s %11s %14s %14s %12s %6s  %s%n",
                    "tipo", "n", "tempo(ms)", "desvio(ms)", "comparacoes", "trocas",
                    "memoria(B)", "recur", "ordenado");

            for (TipoDado tipo : TipoDado.values()) {
                for (int n : TAMANHOS) {
                    if (quadratico && n > LIMITE_QUADRATICO) {
                        continue;
                    }
                    int[] original = DataGenerator.gerar(tipo, n);
                    Resultado r = medir(alg, original);

                    System.out.printf("  %-10s %-9d %12.4f %11.4f %14d %14d %12d %6d  %s%n",
                            tipo.getRotulo(), n, r.mediaMs, r.desvioMs, r.comparacoes, r.trocas,
                            r.memoriaAux, r.profundidade, r.ordenado ? "sim" : "NÃO!");
                }
            }
        }
    }

    private static Resultado medir(AbstractSort alg, int[] original) {
        double[] temposMs = new double[EXECUCOES - WARMUP];
        Resultado r = new Resultado();
        r.ordenado = true;

        for (int exec = 0; exec < EXECUCOES; exec++) {
            int[] copia = original.clone(); // Cada execução recebe a mesma entrada.
            alg.sort(copia);
            r.ordenado &= alg.isSorted(copia);

            if (exec >= WARMUP) { // A 1° execução é descartada.
                temposMs[exec - WARMUP] = alg.getExecutionTime() / 1_000_000.0; // ns -> ms (divisão real)
            }
            // Comparações e trocas são determinísticas para a mesma entrada.
            r.comparacoes = alg.getComparisons();
            r.trocas = alg.getSwaps();
            r.memoriaAux = alg.getAuxMemoryBytes();
            r.profundidade = alg.getMaxRecursionDepth();
        }

        double soma = 0;
        for (double t : temposMs) {
            soma += t;
        }
        r.mediaMs = soma / temposMs.length;
        double var = 0;
        for (double t : temposMs) {
            var += (t - r.mediaMs) * (t - r.mediaMs);
        }
        r.desvioMs = Math.sqrt(var / temposMs.length);
        return r;
    }

    private static void imprimirAmbiente() {
        Runtime rt = Runtime.getRuntime();
        System.out.println("Ambiente de teste (copie para a seção Metodologia do relatório):");
        System.out.println("  Java:       " + System.getProperty("java.version")
                + " (" + System.getProperty("java.vm.name") + ")");
        System.out.println("  SO:         " + System.getProperty("os.name") + " "
                + System.getProperty("os.version") + " / " + System.getProperty("os.arch"));
        System.out.println("  Núcleos:    " + rt.availableProcessors());
        System.out.println("  Heap máx.:  " + (rt.maxMemory() / (1024 * 1024)) + " MB");
    }

    private static final class Resultado {
        double mediaMs;
        double desvioMs;
        long comparacoes;
        long trocas;
        long memoriaAux;
        int profundidade;
        boolean ordenado;
    }
}