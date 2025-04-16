package by.it.group410901.lishchinets.lesson02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class A_VideoRegistrator {

    public static void main(String[] args) {
        A_VideoRegistrator instance = new A_VideoRegistrator();
        double[] events = new double[]{1, 1.1, 1.6, 2.2, 2.4, 2.7, 3.9, 8.1, 9.1, 5.5, 3.7};
        List<Double> starts = instance.calcStartTimes(events, 1); // Расчет моментов старта видеокамеры
        System.out.println(starts); // Вывод результатов
    }

    List<Double> calcStartTimes(double[] events, double workDuration) {
        List<Double> result = new ArrayList<>();

       //Сортировка  события по времени их появления
        Arrays.sort(events);

        int i = 0;
        while (i < events.length) {
            //камера в момент первого незарегистрированного события
            double startTime = events[i];
            result.add(startTime);

            //Пропуск всех событий которые попадают в интервал работы камеры
            while (i < events.length && events[i] <= startTime + workDuration) {
                i++;
            }
        }

        return result;
    }
}
