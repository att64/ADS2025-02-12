package by.it.group410901.lishchinets.lesson03;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class B_Huffman {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream inputStream = B_Huffman.class.getResourceAsStream("dataB.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(inputStream);
        System.out.println(result);
    }

    String decode(InputStream inputStream) throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        Scanner scanner = new Scanner(inputStream);

        // Читаем количество символов и длину закодированной строки
        int count = scanner.nextInt();
        int length = scanner.nextInt();
        scanner.nextLine(); // Переход на следующую строку

        // Читаем коды символов
        Map<String, Character> codes = new HashMap<>();
        for (int i = 0; i < count; i++) {
            String line = scanner.nextLine();
            if (!line.contains(":") || line.length() < 3) {
                throw new IllegalArgumentException("Invalid input format: " + line);
            }
            char symbol = line.charAt(0);
            String code = line.substring(2).trim();
            if (code.isEmpty()) {
                throw new IllegalArgumentException("Code for symbol '" + symbol + "' is empty");
            }
            codes.put(code, symbol);
        }

        // Читаем закодированную строку
        String encodedString = scanner.next();

        // Расшифровываем строку
        StringBuilder currentCode = new StringBuilder();
        for (char c : encodedString.toCharArray()) {
            currentCode.append(c);
            if (codes.containsKey(currentCode.toString())) {
                result.append(codes.get(currentCode.toString()));
                currentCode.setLength(0);
            }
        }

        return result.toString();
    }
}
