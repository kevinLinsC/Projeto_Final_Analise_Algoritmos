package ordenacao.util;

import java.util.Random;

// Gera os três tipos de entrada pedidos no enunciado:
// aleatório (distribuição uniforme), ordenado (crescente) e inversamente ordenado (decrescente).
public final class DataGenerator {
    public enum TipoDado {
        ALEATORIO("aleatorio"),
        ORDENADO("ordenado"),
        INVERSO("inverso");

        private final String rotulo;

        TipoDado(String rotulo) {
            this.rotulo = rotulo;
        }

        public String getRotulo() {
            return rotulo;
        }
    }

    public static final long SEMENTE_PADRAO = 42L;

    private DataGenerator() {
    }

    public static int[] gerar(TipoDado tipo, int n) {
        return gerar(tipo, n, SEMENTE_PADRAO);
    }

    public static int[] gerar(TipoDado tipo, int n, long semente) {
        switch (tipo) {
            case ALEATORIO:
                return aleatorio(n, semente);
            case ORDENADO:
                return crescente(n);
            case INVERSO:
                return decrescente(n);
            default:
                throw new IllegalArgumentException("Tipo de dado desconhecido: " + tipo);
        }
    }

    // Valores uniformes no intervalo [0, n*10).
    public static int[] aleatorio(int n, long semente) {
        Random random = new Random(semente);
        int[] v = new int[n];
        int limite = Math.max(1, n * 10);
        for (int i = 0; i < n; i++) {
            v[i] = random.nextInt(limite);
        }
        return v;
    }

    public static int[] crescente(int n) {
        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = i;
        }
        return v;
    }

    public static int[] decrescente(int n) {
        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = n - i;
        }
        return v;
    }
}