package Java;
import java.io.*;
import java.util.List;

public class BubbleSort {
    public static void main(String[] args) {
        String inputFilePath = "arq.txt";
        String outputFilePath = "Java/Resultados/resultado-bubble-java.txt";
        String sortedFilePath = "arq-bubble-ordenado-java.txt";

        // Criar pasta de resultados
        File resultadosDir = new File("Java/Resultados");
        if (!resultadosDir.exists()) {
            resultadosDir.mkdirs();
        }

        try {
            // Ler números do arquivo
            List<Integer> numbers = FileUtils.readNumbersFromFile(inputFilePath);

            // Medir tempo e memória antes da execução
            long startTime = System.nanoTime();
            long memBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

            // Ordenar usando Bubble Sort
            bubbleSort(numbers);

            // Medir tempo e memória após a execução
            long endTime = System.nanoTime();
            long memAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

            long elapsedTimeMs = (endTime - startTime) / 1_000_000;
            long memUsedKb = (memAfter - memBefore) / 1024;

            // Salvar números ordenados em um arquivo
            FileUtils.writeNumbersToFile(numbers, sortedFilePath);

            // Escrever resultados no arquivo de resultados
            FileUtils.writeResultsToFile(outputFilePath, "Bubble Sort", elapsedTimeMs, memUsedKb);

            System.out.println("Bubble Sort concluído. Tempo: " + elapsedTimeMs + " ms, RAM: " + memUsedKb + " KB");
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
    }

    public static void bubbleSort(List<Integer> arr) {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr.get(j) > arr.get(j + 1)) {
                    // Trocar os elementos
                    int temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                }
            }
        }
    }
}
