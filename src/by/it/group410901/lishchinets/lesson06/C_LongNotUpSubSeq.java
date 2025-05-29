package by.it.group410901.lishchinets.lesson06;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Задача на программирование: наибольшая невозрастающая подпоследовательность

Дано:
    целое число 1<=n<=1E5 ( ОБРАТИТЕ ВНИМАНИЕ НА РАЗМЕРНОСТЬ! )
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] не больше любого предыдущего
    т.е. для всех 1<=j<k, A[i[j]]>=A[i[j+1]].

    В первой строке выведите её длину k,
    во второй - её индексы i[1]<i[2]<…<i[k]
    соблюдая A[i[1]]>=A[i[2]]>= ... >=A[i[n]].

    (индекс начинается с 1)
*/

public class C_LongNotUpSubSeq {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_LongNotUpSubSeq.class.getResourceAsStream("dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int length = instance.getNotUpSeqSize(stream);
        System.out.println(length);
    }

    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);

        // Чтение длины массива и самого массива
        int n = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        // Массив для хранения индексов предыдущих элементов подпоследовательности
        int[] prev = new int[n];
        // Массив для отслеживания индексов конца текущих подпоследовательностей
        int[] dpIndices = new int[n + 1];
        // Длина наибольшей невозрастающей подпоследовательности
        int length = 0;

        for (int i = 0; i < n; i++) {
            // Двоичный поиск позиции для вставки текущего элемента
            int left = 1, right = length;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (array[dpIndices[mid]] >= array[i]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            // Новая длина подпоследовательности
            int newLength = left;
            prev[i] = dpIndices[newLength - 1];
            dpIndices[newLength] = i;

            if (newLength > length) {
                length = newLength;
            }
        }

        // Восстановление индексов подпоследовательности
        List<Integer> resultIndices = new ArrayList<>();
        int index = dpIndices[length];
        while (index != 0) {
            resultIndices.add(0, index + 1); // +1, так как индексация с 1
            index = prev[index];
        }

        // Вывод индексов подпоследовательности
        for (int idx : resultIndices) {
            System.out.print(idx + " ");
        }
        System.out.println();

        return length;
    }
}
