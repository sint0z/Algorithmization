// Данная работа выполнена Стоцким Н.А., Ник (s1ntoz)
// GitHub: https://github.com/sint0z
package ru.shze.linkedlist;

import ru.shze.impl.CircularLinked;
import ru.shze.impl.SingleLinked;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Реализация циклического односвязного списка.
 * Особенность: последний элемент всегда ссылается на первый (tail.next = head).
 *
 * @param <T> тип элементов списка
 */
public class CircularLinkedList<T>
        extends AbstractLinkedList<T>
        implements CircularLinked<T>, SingleLinked<T>, Iterable<T> {

    /**
     * Узел циклического списка: хранит данные и ссылку на следующий узел.
     */
    static class Node<T> implements SingleLinked.Node<T> {
        T item;
        Node<T> next;

        Node(T item, Node<T> next){
            this.item = item;
            this.next = next;
        }
    }

    /**
     * Хвост списка. tail.next всегда указывает на голову (первый элемент).
     * Пустой список: tail == null, size == 0.
     */
    private Node<T> tail;

    /**
     * Конструктор пустого списка.
     */
    public CircularLinkedList() { }

    /**
     * Конструктор, заполняющий список элементами коллекции.
     *
     * @param collection исходная коллекция
     */
    public CircularLinkedList(Collection<? extends T> collection) {
        if (collection != null) {
            for (T element : collection) {
                addLast(element);
            }
        }
    }

    /**
     * Полностью разрывает цикл и очищает все ссылки между узлами.
     * Вызывается из clear().
     */
    @Override
    protected void unlinkAllNodes() {
        if (tail == null) {
            return;
        }

        Node<T> head = tail.next;
        Node<T> current = head;
        // Проходим ровно size узлов, чтобы не уйти в бесконечный цикл.
        for (int i = 0; i < size; i++) {
            Node<T> next = current.next;
            current.item = null;
            current.next = null;
            current = next;
        }

        tail = null;
        size = 0;
    }

    /**
     * {@inheritDoc}
     * Реализовано как сдвиг хвоста на один элемент вперёд:
     * новый tail = старый head.
     */
    @Override
    public void rotate() {
        if (tail != null) {
            tail = tail.next; // head становится хвостом, структура цикла сохраняется
        }
    }

    /**
     * {@inheritDoc}
     * По условию задачи метод всегда возвращает true для циклического списка.
     */
    @Override
    public boolean findCycle() {
        return true;
    }

    /**
     * Поиск элемента по целочисленному значению с учётом цикличности.
     * Проходится по списку ровно size шагов.
     *
     * @param data искомое значение (предполагается, что список параметризован Integer)
     * @return найденный элемент или null, если он отсутствует
     */
    @Override
    public T find(int data) {
        if (tail == null) {
            return null;
        }

        Node<T> head = tail.next;
        Node<T> current = head;

        for (int i = 0; i < size; i++) {
            if (current.item instanceof Integer &&
                    ((Integer) current.item) == data) {
                return current.item;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Разделяет текущий список на два примерно равных циклических списка.
     * Исходный список при этом остаётся неизменным.
     *
     * @return массив из двух новых циклических списков
     */
    @SuppressWarnings("unchecked")
    @Override
    public CircularLinkedList<T>[] splitIntoTwo() {
        CircularLinkedList<T> left  = new CircularLinkedList<>();
        CircularLinkedList<T> right = new CircularLinkedList<>();

        if (tail == null || size == 0) {
            return new CircularLinkedList[]{ left, right };
        }

        Node<T> head = tail.next;
        Node<T> current = head;

        int mid = size / 2;
        int index = 0;

        while (index < size) {
            if (index < mid) {
                left.addLast(current.item);
            } else {
                right.addLast(current.item);
            }
            current = current.next;
            index++;
        }

        return new CircularLinkedList[]{ left, right };
    }

    /**
     * {@inheritDoc}
     * Добавляет все элементы коллекции в конец списка.
     */
    @Override
    public boolean addAll(Collection<? extends T> collection) {
        if (collection == null || collection.isEmpty()) {
            return false;
        }
        for (T element : collection) {
            addLast(element);
        }
        return true;
    }

    /**
     * {@inheritDoc}
     * Добавление в начало: обновляется голова (tail.next).
     */
    @Override
    public void addFirst(T data) {
        if (tail == null) {
            tail = new Node<>(data, null);
            tail.next = tail; // один элемент указывает сам на себя
        } else {
            Node<T> head = tail.next;
            tail.next = new Node<>(data, head);; // новый head
        }
        size++;
    }

    /**
     * {@inheritDoc}
     * Добавление в конец: создаётся новый хвост, tail.next остаётся головой.
     */
    @Override
    public void addLast(T data) {
        if (tail == null) {
            addFirst(data);
        } else {
            Node<T> head = tail.next;
            Node<T> newNode = new Node<>(data, head);
            tail.next = newNode;
            tail = newNode;
            size++;
        }
    }

    /**
     * {@inheritDoc}
     * Удаляет первый элемент (голову списка).
     *
     * @throws NoSuchElementException если список пуст
     */
    @Override
    public T removeFirst() {
        if (tail == null) {
            throw new NoSuchElementException("List is empty");
        }

        Node<T> head = tail.next;
        T value = head.item;

        if (head == tail) {
            // один элемент
            head.item = null;
            head.next = null;
            tail = null;
        } else {
            tail.next = head.next; // новый head
            head.item = null;
            head.next = null;
        }
        size--;
        return value;
    }

    /**
     * {@inheritDoc}
     * Удаляет последний элемент (хвост списка).
     *
     * @throws NoSuchElementException если список пуст
     */
    @Override
    public T removeLast() {
        if (tail == null) {
            throw new NoSuchElementException("List is empty");
        }

        Node<T> head = tail.next;
        if (head == tail) {
            // один элемент
            T value = tail.item;
            tail.item = null;
            tail.next = null;
            tail = null;
            size = 0;
            return value;
        }

        // ищем предпоследний элемент
        Node<T> prev = head;
        while (prev.next != tail) {
            prev = prev.next;
        }

        T value = tail.item;
        prev.next = head;
        tail.item = null;
        tail.next = null;
        tail = prev;
        size--;
        return value;
    }

    /**
     * {@inheritDoc}
     * Удаляет первое вхождение элемента по значению.
     */
    @Override
    public boolean remove(T data) {
        if (tail == null) {
            return false;
        }

        Node<T> head = tail.next;
        Node<T> prev = tail;
        Node<T> current = head;

        for (int i = 0; i < size; i++) {
            if (Objects.equals(current.item, data)) {
                if (current == tail && current == head) {
                    // один элемент
                    current.item = null;
                    current.next = null;
                    tail = null;
                } else {
                    prev.next = current.next;
                    if (current == tail) {
                        tail = prev;
                    }
                    current.item = null;
                    current.next = null;
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * Проверяет наличие элемента в циклическом списке.
     */
    @Override
    public boolean contains(T data) {
        if (tail == null) {
            return false;
        }

        Node<T> head = tail.next;
        Node<T> current = head;

        for (int i = 0; i < size; i++) {
            if (Objects.equals(current.item, data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * Выводит элементы списка в порядке обхода от головы.
     */
    @Override
    public void display() {
        System.out.println(buildString());
    }

    /**
     * Строит строковое представление списка в виде [a, b, c].
     *
     * @return строка с элементами списка
     */
    private String buildString() {
        StringBuilder builder = new StringBuilder("[");
        if (tail != null) {
            Node<T> head = tail.next;
            Node<T> current = head;
            boolean first = true;

            for (int i = 0; i < size; i++) {
                if (!first) {
                    builder.append(", ");
                }
                builder.append(current.item);
                first = false;
                current = current.next;
            }
        }
        builder.append("]");
        return builder.toString();
    }

    /**
     * Итератор по циклическому списку, который делает ровно size шагов,
     * чтобы избежать бесконечного цикла.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int iterated = 0;
            private Node<T> current = (tail != null) ? tail.next : null;

            @Override
            public boolean hasNext() {
                return iterated < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = current.item;
                current = current.next;
                iterated++;
                return value;
            }
        };
    }

    /**
     * Строковое представление списка.
     */
    @Override
    public String toString() {
        return buildString();
    }
}
