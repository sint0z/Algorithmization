// Данная работа выполнена Стоцким Н.А., Ник (s1ntoz)
// GitHub: https://github.com/sint0z
package ru.shze.sort;

/**
 * Интерфейс сортировки массива.
 * Описывает алгоритм упорядочивания элементов массива на месте.
 *
 * @param <T> тип элементов, которые будут сортироваться
 */
public interface Sorter<T> {

    /**
     * Обменивает местами два элемента массива.
     *
     * @param arr    массив, в котором выполняется обмен
     * @param first  индекс первого элемента
     * @param second индекс второго элемента
     * @param <E>    тип элементов массива
     * @throws  ArrayIndexOutOfBoundsException если индексы выходят за пределы массива
     */
    static <E> void swap(E[] arr, int first, int second) {
        E temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

    /**
     * Сортирует переданный массив в соответствии с выбранным алгоритмом.
     * Сортировка выполняется на месте.
     *
     * @param arr массив для сортировки
     */
    void sort(T[] arr);
}
