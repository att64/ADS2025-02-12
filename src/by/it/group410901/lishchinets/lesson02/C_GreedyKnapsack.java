package by.it.group410901.lishchinets.lesson02;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class C_GreedyKnapsack {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        InputStream inputStream = C_GreedyKnapsack.class.getResourceAsStream("greedyKnapsack.txt");
        double totalCost = new C_GreedyKnapsack().calc(inputStream);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость: %f (время выполнения: %d мс)\n", totalCost, finishTime - startTime);
    }

    double calc(InputStream inputStream) {
        // Чтение входных данных
        Scanner input = new Scanner(inputStream);
        int itemCount = input.nextInt(); // кол-во предметов
        int maxWeight = input.nextInt(); // макс вес рюкзака

        // Создаем массив предметов
        Item[] items = new Item[itemCount];
        for (int i = 0; i < itemCount; i++) {
            int cost = input.nextInt();
            int weight = input.nextInt();
            items[i] = new Item(cost, weight);
        }
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает: %d кг.\n", itemCount, maxWeight);
        for (Item item : items) {
            item.calculateValue();
        }
        quickSort(items, 0, items.length - 1);
        double totalValue = 0.0;
        double currentWeight = 0.0;

        for (int i = 0; i < itemCount; i++) {
            if (currentWeight == maxWeight) break;

            Item currentItem = items[i];
            double remainingWeight = maxWeight - currentWeight;

            if (remainingWeight >= currentItem.weight) {
                // Берем весь предмет
                currentWeight += currentItem.weight;
                totalValue += currentItem.cost;
            } else {
                // Берем часть предмета
                totalValue += currentItem.value * remainingWeight;
                currentWeight = maxWeight; // рюкзак заполнен
            }
        }

        System.out.printf("Удалось собрать рюкзак на сумму: %f\n", totalValue);
        return totalValue;
    }

    public static void quickSort(Item[] items, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(items, low, high);
            quickSort(items, low, pivotIndex - 1);
            quickSort(items, pivotIndex + 1, high);
        }
    }

    private static int partition(Item[] items, int low, int high) {
        Item pivot = items[high]; // Опорный элемент
        int smallerIndex = low - 1;

        for (int j = low; j < high; j++) {
            if (items[j].compareTo(pivot) <= 0) {
                smallerIndex++;
                swap(items, smallerIndex, j);
            }
        }

        swap(items, smallerIndex + 1, high);
        return smallerIndex + 1;
    }

    private static void swap(Item[] items, int i, int j) {
        Item temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    // Класс для представления предмета
    private static class Item implements Comparable<Item> {
        int cost;      // Стоимость предмета
        int weight;    // Вес предмета
        double value;  // "Ценность" предмета (стоимость за единицу веса)

        Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
        }

        // Вычисляем "ценность" предмета
        void calculateValue() {
            this.value = (double) cost / weight;
        }

        @Override
        public String toString() {
            return String.format("Item{стоимость=%d, вес=%d, ценность=%.2f}", cost, weight, value);
        }

        @Override
        public int compareTo(Item other) {
            return Double.compare(other.value, this.value); // Сортировка по убыванию ценности
        }
    }
}
