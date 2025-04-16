package by.it.group410901.lishchinets.lesson01;

import java.util.ArrayList;
import java.util.List;

public class FiboC {

    private long startTime = System.currentTimeMillis();

    public static void main(String[] args) {
        by.it.group410901.lishchinets.lesson01.FiboC fibo = new by.it.group410901.lishchinets.lesson01.FiboC();
        int n = 55555;
        int m = 1000;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    long fasterC(long n, int m) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        List<Long> arrk = new ArrayList<>();
        long first = 0;
        long second = 1;
        arrk.add(first);
        arrk.add(second);

        for (int i = 2; i < m * 6; i++) {
            long next = (first + second) % m;
            arrk.add(next);
            first = second;
            second = next;

            if (first == 0 && second == 1) {
                break;
            }
        }
        return arrk.get((int)n % (arrk.size() - 2));
    }


}

