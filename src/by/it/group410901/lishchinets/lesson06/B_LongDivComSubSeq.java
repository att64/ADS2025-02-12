package by.it.group410901.lishchinets.lesson06;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Задача на программирование: наибольшая кратная подпоследовательность

Дано:
    целое число 1≤n≤1000
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] делится на предыдущий
    т.е. для всех 1<=j<k, A[i[j+1]] делится на A[i[j]].

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    4
    3 6 7 12

    Sample Output:
    3
*/

public class B_LongDivComSubSeq {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = B_LongDivComSubSeq.class.getResourceAsStream("dataB.txt");
        B_LongDivComSubSeq instance = new B_LongDivComSubSeq();
        int result = instance.getDivSeqSize(stream);
        System.out.print(result);
    }

    int getDivSeqSize(InputStream stream) throws FileNotFoundException {
        // подготовка к чтению данных
        Scanner scanner = new Scanner(stream);

        // общая длина последовательности
        int n = scanner.nextInt();
        int[] m = new int[n];

        // читаем всю последовательность
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }

        // Сортируем массив для упрощения нахождения кратной последовательности
        Arrays.sort(m);

        // Массив dp, где dp[i] — длина наибольшей кратной подпоследовательности, заканчивающейся на i-ом элементе
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // каждая подпоследовательность минимальной длины равна 1

        // Рассчитываем длины кратных последовательностей для всех индексов
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (m[i] % m[j] == 0) { // если текущий элемент кратен предыдущему
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // Максимальная длина кратной подпоследовательности
        int result = 0;
        for (int length : dp) {
            result = Math.max(result, length);
        }

        return result;
    }
}
