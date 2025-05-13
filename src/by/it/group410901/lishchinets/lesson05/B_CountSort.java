package by.it.group410901.lishchinets.lesson05;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Первая строка содержит число 1<=n<=10000, вторая - n натуральных чисел, не превышающих 10.
Выведите упорядоченную по неубыванию последовательность этих чисел.

При сортировке реализуйте метод со сложностью O(n)

Пример: https://karussell.wordpress.com/2010/03/01/fast-integer-sorting-algorithm-on/
Вольный перевод: http://programador.ru/sorting-positive-int-linear-time/
*/

public class B_CountSort {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = B_CountSort.class.getResourceAsStream("dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result = instance.countSort(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

    int[] countSort(InputStream stream) throws FileNotFoundException {
        // Подготовка к чтению данных
        Scanner scanner = new Scanner(stream);

        // Размер массива
        int n = scanner.nextInt();
        int[] points = new int[n];

        // Читаем числа
        for (int i = 0; i < n; i++) {
            points[i] = scanner.nextInt();
        }

        // Сортировка подсчетом
        return countSort(points, 10); // Числа от 0 до 10
    }

    // Метод сортировки подсчетом
    int[] countSort(int[] array, int maxValue) {
        // Создаем массив счетчиков
        int[] count = new int[maxValue + 1];

        // Заполняем массив счетчиков
        for (int num : array) {
            count[num]++;
        }

        // Восстанавливаем отсортированный массив
        int index = 0;
        for (int i = 0; i <= maxValue; i++) {
            while (count[i] > 0) {
                array[index++] = i;
                count[i]--;
            }
        }

        return array;
    }
}
