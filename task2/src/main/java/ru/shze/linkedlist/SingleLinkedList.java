// Данная работа выполнена Стоцким Н.А., Ник (s1ntoz)
// GitHub: https://github.com/sint0z

package ru.shze.linkedlist;

import ru.shze.impl.SingleLinked;

import java.util.*;

/**
 * Реализация односвязного списка с базовыми операциями
 * добавления, удаления, поиска и поддержкой {@link Iterable}.
 *
 * @param <T> тип элементов списка
 */
public class SingleLinkedList<T> extends AbstractLinkedList<T> implements SingleLinked<T>, Iterable<T> {

    /**
     * Узел односвязного списка: хранит данные и ссылку на следующий узел.
     */
    static class Node<T> implements SingleLinked.Node<T> {
        T item;
        Node<T> next;

        Node(T item, Node<T> next){
            this.item = item;
            this.next = next;
        }
    }

    private Node<T> head;

    /**
     * Возвращает узел по индексу или {@code null}, если индекс некорректен.
     */
    private Node<T> getNode(int index){
        if (index < 0 || index >= size) {
            return null;
        }

        int count = 0;
        Node<T> node = head;

        while (node != null && count != index){
            node = node.next;
            count++;
        }
        return node;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addFirst(T data) {
        head = new Node<>(data, head);
        size++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addLast(T data) {
        Node<T> current = getNode(size - 1);
        if (current == null) {      // список пуст
            addFirst(data);
            return;
        }

        current.next = new Node<>(data, null);
        size++;
    }


    /**
     * {@inheritDoc}
     * Элементы коллекции добавляются в конец списка.
     */
    @Override
    public boolean addAll(Collection<? extends T> collection){
        if (collection != null){
            for (T element : collection){
                addLast(element);
            }
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T removeFirst() {
        if (head == null){
            throw new NoSuchElementException("List is empty"); // как у стандартного LinkedList
        }

        Node<T> current = head;
        head = current.next;
        current.next = null; // Помощь GC
        size--;

        return current.item;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T removeLast() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }

        // один элемент
        if (head.next == null) {
            T value = head.item;
            head = null;
            size = 0;
            return value;
        }

        // идём до предпоследнего
        Node<T> prev = null;
        Node<T> current = head;

        while (current.next != null) {
            prev = current;
            current = current.next;
        }

        // current — последний, prev — предпоследний
        T value = current.item;
        prev.next = null;
        size--;
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(T data) {
        Node<T> node = head;
        Node<T> prev = null;

        while (node != null && !Objects.equals(node.item, data)) {
            prev = node;
            node = node.next;
        }

        if (node == null){
            return false;
        }

        if (prev == null){
            // удаляем голову
            head = head.next;
        } else {
            prev.next = node.next;
        }

        node.next = null;
        size--;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(T data) {
        Node<T> node = head;

        while (node != null){
            if (Objects.equals(node.item, data)){
                return true;
            }
            node = node.next;
        }
        return false;
    }


    /**
     * {@inheritDoc}
     * Формат вывода: элементы через пробел и перевод строки в конце.
     */
    @Override
    public void display() {
        Node<T> node = head;
        StringBuilder sb = new StringBuilder();

        while (node != null) {
            sb.append(node.item);
            if (node.next != null) {
                sb.append(" ");
            }
            node = node.next;
        }

        System.out.println(sb);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void unlinkAllNodes() {
        for (Node<T> node = head; node != null; ) {
            Node<T> next = node.next;
            node.item = null;
            node.next = null;
            node = next;
        }

        head = null;
    }


    /**
     * Возвращает итератор по элементам списка в прямом порядке.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                T value = current.item;
                current = current.next;
                return value;
            }
        };
    }
}
