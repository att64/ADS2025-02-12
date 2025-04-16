package by.it.group410901.lishchinets.lesson01;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить рекурсивный способ вычисления чисел Фибоначчи
 */

public class FiboA {


    private long startTime = System.currentTimeMillis();

    public static void main(String[] args) {
        FiboA fibo = new FiboA();
        int n = 33;
        System.out.printf("calc(%d)=%d \n\t time=%d \n\n", n, fibo.calc(n), fibo.time());

        //вычисление чисел фибоначчи медленным методом (рекурсией)
        fibo = new FiboA();
        n = 34;
        System.out.printf("slowA(%d)=%d \n\t time=%d \n\n", n, fibo.slowA(n), fibo.time());
    }

    private long time() {
        long res = System.currentTimeMillis() - startTime;
        startTime = System.currentTimeMillis();
        return res;
    }

    private int calc(int n) {
        int first = 0;
        int result = 1;
        for(int i = 1; i < n; i++)
        {
            int t = result;
            result += first;
            first = t;
        }
        return result;
    }


    BigInteger slowA(Integer n) {

        if(n <= 1) {
            return BigInteger.valueOf(n);
        }

        return slowA(n - 1).add(slowA(n-2));
    }


}

