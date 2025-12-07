package ru.shze;

import ru.shze.linkedlist.CircularLinkedList;
import ru.shze.linkedlist.DoubleLinkedList;
import ru.shze.linkedlist.SingleLinkedList;

import java.util.Arrays;

public class DisplayTest {

    public static void main(String[] args) {
        testSingleLinkedDisplay();
        testDoubleLinkedDisplay();
        testCircularLinkedDisplay();
    }

    private static void testSingleLinkedDisplay() {
        System.out.println("=== SingleLinkedList display ===");

        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.addFirst(2);
        list.addFirst(1);
        list.addLast(3);
        list.addLast(4);

        // Ожидаемый вывод: [1, 2, 3, 4]
        System.out.print("display(): ");
        list.display();

        System.out.println();
    }

    private static void testDoubleLinkedDisplay() {
        System.out.println("=== DoubleLinkedList display ===");

        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.addFirst(2);
        list.addFirst(1);
        list.addLast(3);
        list.addLast(4);

        // Ожидаемый вывод:
        // display():        [1, 2, 3, 4]
        // displayReverse(): [4, 3, 2, 1]
        System.out.print("display(): ");
        list.display();

        System.out.print("displayReverse(): ");
        list.displayReverse();

        System.out.println();
    }

    private static void testCircularLinkedDisplay() {
        System.out.println("=== CircularLinkedList display ===");

        CircularLinkedList<Integer> list = new CircularLinkedList<>();
        list.addAll(Arrays.asList(1, 2, 3, 4, 5));

        // Ожидаемый вывод: [1, 2, 3, 4, 5]
        System.out.print("display(): ");
        list.display();

        // Проверим, что после rotate() голова сместилась, и display() это отражает.
        list.rotate();
        // Ожидаемый вывод: [2, 3, 4, 5, 1]
        System.out.print("display() после rotate(): ");
        list.display();

        System.out.println();
    }
}
