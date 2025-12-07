package ru.shze.test.sorttest;

import ru.shze.test.ArrayFactory;

public class PartiallySorted75Test extends SortingTest {
    public PartiallySorted75Test() {
        super("PartiallySorted75");
    }
    @Override
    protected Integer[] createArray(int size) {
        return ArrayFactory.partiallySorted75(size);
    }
}
