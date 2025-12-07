package ru.shze.test.sorttest;

import ru.shze.test.ArrayFactory;

public class AlmostSorted90Test extends SortingTest {
    public AlmostSorted90Test() {
        super("AlmostSorted90");
    }
    @Override
    protected Integer[] createArray(int size) {
        return ArrayFactory.almostSorted90(size);
    }
}
