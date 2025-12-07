package ru.shze.test.sorttest;

import ru.shze.test.ArrayFactory;

public class ReversedArrayTest extends SortingTest {
    public ReversedArrayTest() {
        super("Reversed");
    }
    @Override
    protected Integer[] createArray(int size) {
        return ArrayFactory.reversedArray(size);
    }
}
