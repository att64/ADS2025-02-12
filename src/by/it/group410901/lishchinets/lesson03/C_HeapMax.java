package by.it.group410901.lishchinets.lesson03;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class C_HeapMax {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_HeapMax.class.getResourceAsStream("dataC.txt");
        C_HeapMax instance = new C_HeapMax();
        System.out.println("MAX=" + instance.findMaxValue(stream));
    }

    // Эта процедура читает данные из файла, её можно не менять.
    Long findMaxValue(InputStream stream) {
        Long maxValue = 0L;
        MaxHeap heap = new MaxHeap();
        Scanner scanner = new Scanner(stream);
        Integer count = scanner.nextInt();
        scanner.nextLine(); // Переход на следующую строку
        for (int i = 0; i < count; ) {
            String s = scanner.nextLine();
            if (s.equalsIgnoreCase("extractMax")) {
                Long res = heap.extractMax();
                if (res != null && res > maxValue) maxValue = res;
                System.out.println(res);
                i++;
            } else if (s.startsWith("Insert")) {
                String[] parts = s.split(" ");
                if (parts.length == 2) {
                    heap.insert(Long.parseLong(parts[1]));
                }
                i++;
            }
        }
        return maxValue;
    }

    private static class MaxHeap {
        private List<Long> heap = new ArrayList<>();

        private int siftUp(int i) {
            while (i > 0) {
                int parent = (i - 1) / 2;
                if (heap.get(i) > heap.get(parent)) {
                    swap(i, parent);
                    i = parent;
                } else {
                    break;
                }
            }
            return i;
        }

        private int siftDown(int i) {
            int maxIndex = i;
            while (true) {
                int left = 2 * i + 1;
                int right = 2 * i + 2;

                if (left < heap.size() && heap.get(left) > heap.get(maxIndex)) {
                    maxIndex = left;
                }
                if (right < heap.size() && heap.get(right) > heap.get(maxIndex)) {
                    maxIndex = right;
                }

                if (i != maxIndex) {
                    swap(i, maxIndex);
                    i = maxIndex;
                } else {
                    break;
                }
            }
            return i;
        }

        void insert(Long value) {
            heap.add(value);
            siftUp(heap.size() - 1);
        }

        Long extractMax() {
            if (heap.isEmpty()) {
                return null;
            }
            Long max = heap.get(0);
            Long last = heap.remove(heap.size() - 1);
            if (!heap.isEmpty()) {
                heap.set(0, last);
                siftDown(0);
            }
            return max;
        }

        private void swap(int i, int j) {
            Long temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }
    }
}
