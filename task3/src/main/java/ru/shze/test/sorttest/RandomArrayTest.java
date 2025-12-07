package ru.shze.test.sorttest;

import ru.shze.test.ArrayFactory;

public class RandomArrayTest extends SortingTest {
    public RandomArrayTest() {
        super("Random");
    }
    @Override
    protected Integer[] createArray(int size) {
        return ArrayFactory.randomArray(size);
    }
}

