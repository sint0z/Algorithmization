package ru.shze.test.sorttest;

import ru.shze.test.ArrayFactory;

public class ManyDuplicatesTest extends SortingTest {
    public ManyDuplicatesTest() {
        super("ManyDuplicates");
    }
    @Override
    protected Integer[] createArray(int size) {
        return ArrayFactory.manyDuplicates(size);
    }
}
