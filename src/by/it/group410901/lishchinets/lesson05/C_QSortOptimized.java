package by.it.group410901.lishchinets.lesson05;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Видеорегистраторы и площадь 2.
Условие то же что и в задаче А.

    По сравнению с задачей A доработайте алгоритм так, чтобы
    1) он оптимально использовал время и память:
        - за стек отвечает элиминация хвостовой рекурсии
        - за сам массив отрезков - сортировка на месте
        - рекурсивные вызовы должны проводиться на основе 3-разбиения

    2) при поиске подходящих отрезков для точки реализуйте метод бинарного поиска
    для первого отрезка решения, а затем найдите оставшуюся часть решения
    (т.е. отрезков, подходящих для точки, может быть много)

Sample Input:
2 3
0 5
7 10
1 6 11
Sample Output:
1 0 0
*/

public class C_QSortOptimized {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_QSortOptimized.class.getResourceAsStream("dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result = instance.getAccessory2(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        // Подготовка к чтению данных
        Scanner scanner = new Scanner(stream);

        // Чтение числа отрезков и точек
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Segment[] segments = new Segment[n];
        int[] points = new int[m];
        int[] result = new int[m];

        // Чтение отрезков
        for (int i = 0; i < n; i++) {
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }

        // Чтение точек
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }

        // Сортируем отрезки по начальной точке
        Arrays.sort(segments);

        // Для каждой точки ищем количество отрезков, которые её покрывают
        for (int i = 0; i < m; i++) {
            result[i] = countSegmentsForPoint(segments, points[i]);
        }

        return result;
    }

    // Метод для поиска количества отрезков, которые содержат точку
    private int countSegmentsForPoint(Segment[] segments, int point) {
        int count = 0;

        // Используем бинарный поиск для нахождения первого отрезка, который может содержать точку
        int left = 0;
        int right = segments.length - 1;
        int firstIndex = -1;

        // Бинарный поиск на основе начала отрезка
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (segments[mid].start <= point) {
                firstIndex = mid;
                left = mid + 1; // Ищем правее
            } else {
                right = mid - 1; // Ищем левее
            }
        }

        // Теперь проверяем все отрезки начиная с firstIndex и считаем те, которые содержат точку
        if (firstIndex != -1) {
            for (int i = firstIndex; i < segments.length; i++) {
                if (segments[i].start > point) {
                    break;
                }
                if (segments[i].start <= point && segments[i].stop >= point) {
                    count++;
                }
            }
        }

        return count;
    }

    // Класс отрезка с реализацией интерфейса Comparable для сортировки
    private class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment o) {
            return Integer.compare(this.start, o.start);
        }
    }
}
