// Данная работа выполнена Стоцким Н.А., Ник (s1ntoz)
// GitHub: https://github.com/sint0z
package ru.shze.sort;

/**
 * Сортировщик, реализующий вариацию сортировки расчёской (comb‑подобный алгоритм)
 * для массива целых чисел. Использует убывающий шаг сравнения элементов,
 * вычисляемый на основе коэффициента {@link #FACTOR}.
 */
public class ComdSorter implements Sorter<Integer> {

    /**
     * Коэффициент уменьшения шага (gap) между сравниваемыми элементами.
     * Чем больше коэффициент, тем быстрее шаг уменьшается к 1.
     */
    private static final double FACTOR = 1.247d;

    /**
     * Сортирует переданный массив целых чисел.
     * На каждом проходе алгоритм вычисляет текущий шаг {@code gap} и
     * выполняет серию обменов элементов, если более поздний элемент
     * меньше более раннего. Процедура повторяется до тех пор, пока
     * значение шага {@code gapFactor} не станет меньше либо равно 1.
     *
     * @param arr массив {@link Integer}, который требуется отсортировать
     */
    @Override
    public void sort(Integer[] arr) {
        int length = arr.length;
        double gapFactor = length / FACTOR;

        while (gapFactor > 1) {
            int gap = (int) Math.round(gapFactor);

            for (int i = 0; i < gap - 1; i++) {
                for (int j = gap; j < length; j++) {
                    if (arr[j] < arr[i]) {
                        Sorter.swap(arr, i, j);
                    }
                }
            }

            gapFactor = gapFactor / FACTOR;
        }
    }
}
