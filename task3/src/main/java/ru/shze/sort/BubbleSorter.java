// Данная работа выполнена Стоцким Н.А., Ник (s1ntoz)
// GitHub: https://github.com/sint0z
package ru.shze.sort;

/**
 * Простейшая сортировка массива целых чисел методом «пузырька».
 * Для каждой позиции i ищет минимальный элемент в правой части
 * массива и обменивает его с текущим элементом, упорядочивая
 * массив по возрастанию.
 */
public class BubbleSorter implements Sorter<Integer> {

    /**
     * Сортирует переданный массив по возрастанию.
     * Внешний цикл фиксирует позицию i, внутренний перебирает элементы справа
     * и при необходимости меняет местами arr[i] и arr[j].
     *
     * @param arr массив {@link Integer}, который требуется отсортировать
     */
    @Override
    public void sort(Integer[] arr) {
        int arrLength = arr.length;

        for (int i = 0; i < arrLength - 1; i++) {
            for (int j = i + 1; j < arrLength; j++) {
                if (arr[j] < arr[i]) {
                    Sorter.swap(arr, i, j);
                }
            }
        }
    }
}
