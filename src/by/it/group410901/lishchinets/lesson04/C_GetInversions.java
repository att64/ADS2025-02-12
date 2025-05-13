package by.it.group410901.lishchinets.lesson04;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Рассчитать число инверсий одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо посчитать число пар индексов 1<=i<j<n, для которых A[i]>A[j].

Sample Input:
5
2 3 9 2 9
Sample Output:
2
*/

public class C_GetInversions {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_GetInversions.class.getResourceAsStream("dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        int result = instance.calc(stream);
        System.out.print(result);
    }

    int calc(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);

        // Размер массива
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        // Создаем временный массив для сортировки слиянием
        int[] temp = new int[n];

        // Считаем количество инверсий
        return mergeSortAndCount(a, temp, 0, n - 1);
    }

    // Функция сортировки слиянием, которая возвращает количество инверсий
    int mergeSortAndCount(int[] array, int[] temp, int left, int right) {
        int count = 0;

        if (left < right) {
            int mid = left + (right - left) / 2;

            // Считаем инверсии в левой половине
            count += mergeSortAndCount(array, temp, left, mid);

            // Считаем инверсии в правой половине
            count += mergeSortAndCount(array, temp, mid + 1, right);

            // Считаем инверсии во время слияния
            count += mergeAndCount(array, temp, left, mid, right);
        }

        return count;
    }

    // Функция для слияния двух отсортированных подмассивов и подсчета инверсий
    int mergeAndCount(int[] array, int[] temp, int left, int mid, int right) {
        int i = left;    // Индекс левого подмассива
        int j = mid + 1; // Индекс правого подмассива
        int k = left;    // Индекс объединенного подмассива
        int count = 0;

        // Объединяем два подмассива
        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];

                // Все оставшиеся элементы в левом подмассиве больше текущего элемента из правого подмассива
                count += (mid - i + 1);
            }
        }

        // Копируем оставшиеся элементы из левого подмассива (если есть)
        while (i <= mid) {
            temp[k++] = array[i++];
        }

        // Копируем оставшиеся элементы из правого подмассива (если есть)
        while (j <= right) {
            temp[k++] = array[j++];
        }

        // Копируем объединенный массив обратно в исходный массив
        for (i = left; i <= right; i++) {
            array[i] = temp[i];
        }

        return count;
    }
}
