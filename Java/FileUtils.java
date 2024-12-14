package Java;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static List<Integer> readNumbersFromFile(String filePath) throws IOException {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(line.trim()));
            }
        }
        return numbers;
    }

    public static void writeNumbersToFile(List<Integer> numbers, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int number : numbers) {
                writer.write(number + "\n");
            }
        }
    }

    public static void writeResultsToFile(String filePath, String sortMethod, long timeMs, long memoryKb) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(sortMethod + " - Tempo: " + timeMs + " ms, RAM: " + memoryKb + " KB\n");
        }
    }
}
