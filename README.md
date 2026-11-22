# Batalha dos Algoritmos de Ordenação

Projeto Final da disciplina **Projeto e Análise de Algoritmos** — Prof. Alexandre Barbosa de Souza.

Implementação, medição e comparação de cinco algoritmos de ordenação em Java, relacionando os resultados experimentais com a análise assintótica.

## Integrantes

| Aluno | Algoritmo | Classe | Complexidade (caso médio) |
|---|---|---|---|
| Kevin Lins Carminati Andrade       | Bubble Sort    | `BubbleSort.java`    | O(n²)      |
| Pedro Lucas Sipriano Do Nascimento | Insertion Sort | `InsertionSort.java` | O(n²)      |
| Tiago Lopes Alves                  | Selection Sort | `SelectionSort.java` | O(n²)      |
| Diogo Ventorin De Freitas          | Merge Sort     | `MergeSort.java`     | O(n log n) |
| João Victor Wuthovsky Klein        | Quick Sort     | `QuickSort.java`     | O(n log n) |

## Estrutura

```
batalha-ordenacao/
├── src/ordenacao/
│   ├── SortingAlgorithm.java      # interface padrão do enunciado
│   ├── AbstractSort.java          # contadores, medição de tempo e isSorted()
│   ├── Main.java                  # programa principal (resultados no console)
│   ├── algoritmos/
│   │   ├── BubbleSort.java
│   │   ├── InsertionSort.java
│   │   ├── SelectionSort.java
│   │   ├── MergeSort.java
│   │   └── QuickSort.java
│   └── util/
│       └── DataGenerator.java     # entradas aleatória, crescente e decrescente
```

## Requisitos

- Java 11 ou superior (JDK)

## Como executar

Todos os comandos são executados na pasta raiz do projeto.

**1. Compilar**

```bash
javac -d out src/ordenacao/*.java src/ordenacao/algoritmos/*.java src/ordenacao/util/*.java
```

**2. Rodar o experimento**

```bash
java -cp out ordenacao.Main
```

Os resultados são exibidos no console, uma tabela por algoritmo.

## Metodologia do experimento

- **Tamanhos:** n = 100, 1.000, 5.000, 10.000, 50.000 e 100.000. Os algoritmos O(n²) vão até 50.000.
- **Tipos de dado:** aleatório, crescente e decrescente.
- **Repetições:** 5 execuções por combinação. A 1ª é descartada (warm-up da JVM) e o tempo final é a média das 4 restantes, com desvio padrão.
- **Mesma entrada para todos:** cada execução recebe uma cópia do mesmo vetor original.
- **Tempo:** `System.nanoTime()`, medido apenas em volta da ordenação.

### Métricas coletadas

| Coluna | Significado |
|---|---|
| `comparacoes` | comparações entre elementos do vetor |
| `trocas` | trocas (Bubble, Selection, Quick) ou movimentos/escritas (Insertion, Merge) |
| `tempo(ms)` / `desvio(ms)` | tempo médio e desvio das 4 execuções válidas |
| `memoria(B)` | memória extra alocada (vetor auxiliar do Merge Sort = 4n bytes) |
| `recur` | profundidade máxima da pilha (Merge e Quick) |
| `ordenado` | resultado de `isSorted()` após a ordenação |

## Decisões de implementação

- **Bubble Sort** com parada antecipada: em vetor já ordenado faz uma única passada, ou seja, Ω(n).
- **Insertion Sort** conta deslocamentos como movimentos. É linear em vetor ordenado.
- **Selection Sort** faz sempre n(n−1)/2 comparações, mas no máximo n−1 trocas.
- **Merge Sort** aloca um único vetor auxiliar de tamanho n, em vez de um vetor novo a cada chamada.
- **Quick Sort** usa pivô pela mediana de três e faz recursão apenas na parte menor. Isso evita o pior caso O(n²) em vetores ordenados ou invertidos e o `StackOverflowError` com n = 100.000.
