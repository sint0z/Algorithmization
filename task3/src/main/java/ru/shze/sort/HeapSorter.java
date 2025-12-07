// Данная работа выполнена Стоцким Н.А., Ник (s1ntoz)
// GitHub: https://github.com/sint0z
package ru.shze.sort;

public class HeapSorter implements Sorter<Integer> {

    /**
     * Восстанавливает свойство максимальной кучи для поддерева.
     *
     * @param arr      массив-куча
     * @param heapSize актуальный размер кучи
     * @param root     индекс корня поддерева
     */
    private void heapify(Integer[] arr, int heapSize, int root) {
        int largest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        if (left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != root) {
            Sorter.swap(arr, root, largest);
            heapify(arr, heapSize, largest);
        }
    }

    /**
     * Пирамидальная сортировка массива по возрастанию.
     *
     * @param arr сортируемый массив
     */
    @Override
    public void sort(Integer[] arr) {
        int n = arr.length;

        // построение max-кучи
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // извлечение максимума в конец массива
        for (int i = n - 1; i >= 0; i--) {
            Sorter.swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }
}
