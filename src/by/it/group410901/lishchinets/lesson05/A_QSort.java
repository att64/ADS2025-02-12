package by.it.group410901.lishchinets.lesson05;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Видеорегистраторы и площадь.
Sample Input:
2 3
0 5
7 10
1 6 11
Sample Output:
1 0 0
*/

public class A_QSort {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = A_QSort.class.getResourceAsStream("dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result = instance.getAccessory(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);

        // Количество отрезков
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];

        // Количество точек
        int m = scanner.nextInt();
        int[] points = new int[m];
        int[] result = new int[m];

        // Читаем отрезки
        for (int i = 0; i < n; i++) {
            int start = scanner.nextInt();
            int stop = scanner.nextInt();
            segments[i] = new Segment(start, stop);
        }

        // Читаем точки
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }

        // Сортируем отрезки и точки
        Arrays.sort(segments);
        Arrays.sort(points);

        // Используем индексы для прохода
        int segmentIndex = 0;
        int[] coverage = new int[m];

        // Для каждой точки определяем число перекрытий
        for (int i = 0; i < m; i++) {
            while (segmentIndex < n && segments[segmentIndex].stop < points[i]) {
                segmentIndex++;
            }

            // Подсчитываем количество отрезков, которые перекрывают точку
            int count = 0;
            for (int j = segmentIndex; j < n && segments[j].start <= points[i]; j++) {
                if (segments[j].stop >= points[i]) {
                    count++;
                }
            }
            coverage[i] = count;
        }

        return coverage;
    }

    // Класс Segment для хранения отрезков
    private static class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment o) {
            // Сортировка по началу отрезка
            return Integer.compare(this.start, o.start);
        }
    }

}
