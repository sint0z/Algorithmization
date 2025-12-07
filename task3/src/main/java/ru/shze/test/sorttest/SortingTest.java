// Данная работа выполнена Стоцким Н.А., Ник (s1ntoz)
// GitHub: https://github.com/sint0z
package ru.shze.test.sorttest;

import ru.shze.sort.Sorter;
import ru.shze.test.Test;
import ru.shze.test.TestParam;

/**
 * Базовый класс для тестов сортировки с разными типами входных массивов.
 * Конкретные реализации задают способ генерации данных через {@link #createArray(int)}.
 */
public abstract class SortingTest extends Test {

    /**
     * Создаёт тест сортировки с указанным именем (типом массива).
     *
     * @param name человекочитаемое название теста
     */
    public SortingTest(String name) {
        super(name);
    }

    /**
     * Создаёт новый массив заданного размера для теста.
     * Реализация определяет характер данных: случайные, частично отсортированные и т.п.
     *
     * @param size требуемый размер массива
     * @return новый массив входных данных для сортировки
     */
    protected abstract Integer[] createArray(int size);

    /**
     * Выполняет серию прогонов сортировки и измеряет лучшее время.
     * На каждом прогоне генерируется новый массив, сортируется и
     * фиксируется минимальное время среди всех повторов.
     *
     * @param sorter алгоритм сортировки
     * @param param  параметры теста (размер массива и число прогонов)
     * @return лучшее время выполнения в миллисекундах
     */
    @Override
    public int test(Sorter<Integer> sorter, TestParam param) {
        long bestTime = Long.MAX_VALUE;
        for (int i = 0; i < param.loops(); i++) {
            Integer[] data = createArray(param.size());
            long start = System.nanoTime();
            sorter.sort(data);
            long end = System.nanoTime();
            long elapsed = end - start;
            if (elapsed < bestTime) {
                bestTime = elapsed;
            }
        }

        return (int) (bestTime / 1_000_000);
    }
}
