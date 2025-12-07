package ru.shze;

import ru.shze.sort.BubbleSorter;
import ru.shze.sort.ComdSorter;
import ru.shze.sort.HeapSorter;
import ru.shze.sort.Sorter;
import ru.shze.test.TestParam;
import ru.shze.test.Tester;
import ru.shze.test.sorttest.*;

import java.util.List;

public class Main {
    static List<SortingTest> TESTS = List.of(
            new RandomArrayTest(),
            new ManyDuplicatesTest(),
            new PartiallySorted75Test(),
            new AlmostSorted90Test(),
            new ReversedArrayTest()
    );


    public static void main(String[] args) {

        List<Sorter<Integer>> sorters = List.of(
                new BubbleSorter(),
                new ComdSorter(),
                new HeapSorter()
        );

        for(Sorter<Integer> sorter : sorters){
            Tester tester = new Tester(sorter, TESTS, new TestParam[]{
                    new TestParam(10, 5),
                    new TestParam(100, 5),
                    new TestParam( 120, 5)
            });
            tester.run();
        }
    }
}
