package Java;
import java.io.*;
import java.util.List;

public class QuickSort {
    public static void main(String[] args) {
        String inputFilePath = "arq.txt";
        String outputFilePath = "Java/Resultados/resultado-quick-java.txt";
        String sortedFilePath = "arq-quick-ordenado-java.txt";

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

            // Ordenar usando Quick Sort
            quickSort(numbers, 0, numbers.size() - 1);

            // Medir tempo e memória após a execução
            long endTime = System.nanoTime();
            long memAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

            long elapsedTimeMs = (endTime - startTime) / 1_000_000;
            long memUsedKb = (memAfter - memBefore) / 1024;

            // Salvar números ordenados em um arquivo
            FileUtils.writeNumbersToFile(numbers, sortedFilePath);

            // Escrever resultados no arquivo de resultados
            FileUtils.writeResultsToFile(outputFilePath, "Quick Sort", elapsedTimeMs, memUsedKb);

            System.out.println("Quick Sort concluído. Tempo: " + elapsedTimeMs + " ms, RAM: " + memUsedKb + " KB");
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
    }

    public static void quickSort(List<Integer> arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            // Ordenar os subarrays
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(List<Integer> arr, int low, int high) {
        int pivot = arr.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr.get(j) < pivot) {
                i++;
                // Trocar os elementos
                int temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            }
        }

        // Trocar o pivot para sua posição correta
        int temp = arr.get(i + 1);
        arr.set(i + 1, arr.get(high));
        arr.set(high, temp);

        return i + 1;
    }
}
