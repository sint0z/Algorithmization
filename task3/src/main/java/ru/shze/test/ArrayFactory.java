// Данная работа выполнена Стоцким Н.А., Ник (s1ntoz)
// GitHub: https://github.com/sint0z
package ru.shze.test;

import java.util.*;

/**
 * Фабрика тестовых массивов для проверки алгоритмов сортировки.
 * Содержит статические методы генерации массивов с различными распределениями значений.
 */
public interface ArrayFactory {

    /**
     * Общий генератор случайных чисел для всех фабричных методов.
     */
    Random RANDOM = new Random();

    /**
     * Создаёт массив случайных целых чисел от 0 (включительно) до {@code size} (не включая).
     *
     * @param size требуемый размер массива
     * @return новый массив случайных значений
     */
    static Integer[] randomArray(int size) {
        Integer[] a = new Integer[size];
        for (int i = 0; i < size; i++) {
            a[i] = RANDOM.nextInt(size);
        }
        return a;
    }

    /**
     * Создаёт массив, отсортированный на 75%: первая часть упорядочена по возрастанию,
     * оставшиеся 25% элементов перемешаны. Удобно для тестирования алгоритмов
     * на частично отсортированных данных.
     *
     * @param size размер массива
     * @return частично отсортированный массив
     */
    static Integer[] partiallySorted75(int size) {
        Integer[] a = randomArray(size);
        Arrays.sort(a);
        int fixed = (int) (size * 0.75);

        Integer[] tail = Arrays.copyOfRange(a, fixed, size);
        shuffle(tail);
        System.arraycopy(tail, 0, a, fixed, tail.length);
        return a;
    }

    /**
     * Создаёт массив, отсортированный по убыванию (полностью обратный
     * по отношению к возрастающему порядку).
     *
     * @param size размер массива
     * @return массив, отсортированный по убыванию
     */
    static Integer[] reversedArray(int size) {
        Integer[] a = randomArray(size);
        Arrays.sort(a, Comparator.reverseOrder());
        return a;
    }

    /**
     * Создаёт массив с большим числом повторяющихся значений:
     * используется не более 10% уникальных элементов, остальные — дубликаты.
     *
     * @param size размер массива
     * @return массив со множеством дубликатов
     */
    static Integer[] manyDuplicates(int size) {
        int uniqueCount = Math.max(1, (int) (size * 0.1));
        Integer[] values = new Integer[uniqueCount];
        for (int i = 0; i < uniqueCount; i++) {
            values[i] = i;
        }
        Integer[] a = new Integer[size];
        for (int i = 0; i < size; i++) {
            a[i] = values[RANDOM.nextInt(uniqueCount)];
        }
        return a;
    }

    /**
     * Создаёт массив, отсортированный на 90%: первые 90% элементов упорядочены,
     * оставшиеся 10% перемешаны. Полезно для оценки поведения адаптивных сортировок.
     *
     * @param size размер массива
     * @return почти отсортированный массив
     */
    static Integer[] almostSorted90(int size) {
        Integer[] a = randomArray(size);
        Arrays.sort(a);
        int fixed = (int) (size * 0.90);
        Integer[] tail = Arrays.copyOfRange(a, fixed, size);
        shuffle(tail);
        System.arraycopy(tail, 0, a, fixed, tail.length);
        return a;
    }

    /**
     * Перемешивает элементы массива
     *
     * @param arr массив для перемешивания
     */
    private static void shuffle(Integer[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int j = RANDOM.nextInt(i + 1);
            Integer tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
}
