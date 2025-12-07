// Данная работа выполнена Стоцким Н.А., Ник (s1ntoz)
// GitHub: https://github.com/sint0z
package ru.shze.test;

import ru.shze.sort.Sorter;
import ru.shze.test.sorttest.SortingTest;

import java.util.List;

/**
 * Драйвер для запуска набора тестов сортировки над заданным алгоритмом.
 * Перебирает сценарии тестирования и параметры размера, выводя
 * результаты в консоль в удобочитаемом формате.
 */
public class Tester {

    /**
     * Набор параметров по умолчанию: разные размеры массива
     * и фиксированное число прогонов для усреднения времени.
     */
    private static final TestParam[] DEFAULT_TEST_PARAMS = {
            new TestParam(1000, 5),
            new TestParam(10000, 5),
            new TestParam(100000, 5)
    };

    /**
     * Тестируемый алгоритм сортировки.
     */
    private final Sorter<Integer> sorter;

    /**
     * Список сценариев тестирования (типы входных массивов).
     */
    private final List<SortingTest> tests;

    /**
     * Набор параметров тестирования: размеры массивов и число прогонов.
     */
    private final TestParam[] params;

    /**
     * Создаёт тестер с явным набором параметров.
     *
     * @param sorter алгоритм сортировки
     * @param tests  список сценариев тестирования
     * @param params массив параметров (размер и число прогонов)
     */
    public Tester(Sorter<Integer> sorter, List<SortingTest> tests, TestParam[] params) {
        this.sorter = sorter;
        this.tests = tests;
        this.params = params;
    }

    /**
     * Создаёт тестер с параметрами по умолчанию.
     *
     * @param sorter алгоритм сортировки
     * @param tests  список сценариев тестирования
     */
    public Tester(Sorter<Integer> sorter, List<SortingTest> tests) {
        this.sorter = sorter;
        this.tests = tests;
        this.params = DEFAULT_TEST_PARAMS;
    }

    /**
     * Запускает все тесты для текущего сортировщика.
     * Для каждого сценария перебирает набор параметров и выводит
     * в консоль размер массива, число прогонов и лучшее измеренное время.
     */
    public void run() {
        for (Test test : tests) {
            System.out.println("Test type: " + test.name);
            for (TestParam param : params) {
                int timeMs = test.test(sorter, param);
                System.out.printf(
                        " Sorter->[%s] size=%d, loops=%d, bestTime=%d ms%n",
                        sorter.getClass().getSimpleName(),
                        param.size(),
                        param.loops(),
                        timeMs
                );
            }
            System.out.println();
        }
    }
}
