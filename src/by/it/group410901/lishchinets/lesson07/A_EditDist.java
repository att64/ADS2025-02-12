package by.it.group410901.lishchinets.lesson07;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Рекурсивно вычислить расстояние редактирования двух данных непустых строк

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0

    Sample Input 2:
    short
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5
*/

public class A_EditDist {

    int getDistanceEdinting(String one, String two) {
        int m = one.length();
        int n = two.length();

        // Матрица для хранения расстояний
        int[][] dp = new int[m + 1][n + 1];

        // Заполняем базовые случаи
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i; // Удаление всех символов из `one`
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j; // Вставка всех символов в `one`
        }

        // Вычисление расстояния Левенштейна
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int cost = (one.charAt(i - 1) == two.charAt(j - 1)) ? 0 : 1;

                dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j] + 1, // Удаление
                                dp[i][j - 1] + 1), // Вставка
                        dp[i - 1][j - 1] + cost   // Замена
                );
            }
        }

        // Возвращаем расстояние
        return dp[m][n];
    }

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = A_EditDist.class.getResourceAsStream("dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }
}
