// Данная работа выполнена Стоцким Н.А., Ник (s1ntoz)
// GitHub: https://github.com/sint0z
package ru.shze.linkedlist;

import ru.shze.impl.DoubleLinked;
import ru.shze.impl.SingleLinked;

import java.util.*;

/**
 * Реализация двусвязного списка на основе абстрактного базового класса.
 * Поддерживает операции вставки и удаления по краям и по индексу,
 * обход в прямом и обратном порядке, а также работу с коллекциями.
 *
 * @param <T> тип элементов списка
 */
public class DoubleLinkedList<T>
        extends AbstractLinkedList<T>
        implements DoubleLinked<T>, Iterable<T> {

    /**
     * Узел двусвязного списка: хранит значение и ссылки на соседние узлы.
     */
    static class Node<T> implements SingleLinked.Node<T> {
        T value;
        Node<T> prev, next;

        Node(T value, Node<T> prev){
            this.value = value;
            this.prev = prev;
            this.next = null;
        }
    }

    /**
     * Ссылка на первый узел списка (голову).
     */
    private Node<T> headNode = null;

    /**
     * Ссылка на последний узел списка (хвост).
     */
    private Node<T> tailNode = null;

    /**
     * Создаёт пустой двусвязный список.
     */
    public DoubleLinkedList(){ }

    /**
     * Создаёт список и добавляет в него все элементы из переданной коллекции.
     *
     * @param collection исходная коллекция элементов
     */
    public DoubleLinkedList(Collection<? extends T> collection){
        super(collection);
    }

    /**
     * {@inheritDoc}
     * Новый элемент становится головой списка.
     */
    @Override
    public void addFirst(T element){
        if (headNode == null){
            headNode = new Node<>(element, null);
            tailNode = headNode;
        } else {
            headNode.prev = new Node<>(element, null);
            headNode.prev.next = headNode;
            headNode = headNode.prev;
        }
        size++;
    }

    /**
     * {@inheritDoc}
     * Новый элемент становится хвостом списка.
     */
    @Override
    public void addLast(T element){
        if (headNode == null){
            headNode = new Node<>(element, null);
            tailNode = headNode;
        } else {
            tailNode.next = new Node<>(element, tailNode);
            tailNode = tailNode.next;
        }
        size++;
    }

    /**
     * {@inheritDoc}
     * Удаляет первый узел и корректно обновляет ссылки головы и хвоста.
     *
     * @throws NoSuchElementException если список пуст
     */
    @Override
    public T removeFirst() {
        Node<T> first = headNode;
        if (first == null){
            throw new NoSuchElementException("List is empty");
        }

        final T value = first.value;
        final Node<T> next = first.next;
        first.value = null;
        first.next = null; // помощь GC
        headNode = next;
        if (next == null) {
            // список стал пустым
            tailNode = null;
        } else {
            next.prev = null;
        }

        size--;
        return value;
    }

    /**
     * {@inheritDoc}
     * Удаляет последний узел и корректно обновляет ссылки хвоста и головы.
     *
     * @throws NoSuchElementException если список пуст
     */
    @Override
    public T removeLast() {
        Node<T> last = tailNode;
        if (last == null){
            throw new NoSuchElementException("List is empty");
        }

        final T value = last.value;
        final Node<T> prev = last.prev;
        last.value = null;
        last.prev = null; // помощь GC

        tailNode = prev;
        if (prev == null) {
            // удалили единственный элемент
            headNode = null;
        } else {
            prev.next = null;
        }
        size--;
        return value;
    }

    /**
     * Возвращает узел по индексу или {@code null}, если индекс некорректен.
     *
     * @param index индекс искомого узла
     * @return узел или {@code null}, если индекс вне диапазона
     */
    private Node<T> getNode(int index){
        if (index < 0 || index >= size) {
            return null;
        }

        int count = 0;
        Node<T> node = headNode;
        while (node != null && count != index){
            node = node.next;
            count++;
        }
        return node;
    }

    /**
     * {@inheritDoc}
     * Вставляет элемент перед узлом с указанным индексом
     */
    @Override
    public boolean add(int index, T element){
        if (index < 0 || index > size) {
            return false;
        }

        if (index == 0) {
            addFirst(element);
            return true;
        }
        if (index == size) {
            addLast(element);
            return true;
        }

        Node<T> node = getNode(index);
        if (node == null) {
            return false;
        }

        Node<T> prev = node.prev;
        Node<T> newNode = new Node<>(element, prev);
        newNode.next = node;

        prev.next = newNode;
        node.prev = newNode;
        size++;
        return true;
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param element добавляемый элемент
     * @return {@code true}, если элемент добавлен
     */
    public boolean add(T element){
        return add(size, element);
    }

    /**
     * Вставляет все элементы коллекции, начиная с указанного индекса.
     *
     * @param index      индекс, с которого начинается вставка
     * @param collection коллекция элементов
     * @return {@code true}, если хотя бы один элемент был добавлен
     */
    public boolean addAll(int index, Collection<? extends T> collection){
        if (collection != null){
            for (T element : collection){
                add(index++, element);
            }
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * Элементы коллекции добавляются в конец списка в том порядке, в котором
     * их предоставляет итератор коллекции.
     */
    @Override
    public boolean addAll(Collection<? extends T> collection){
        return addAll(size, collection);
    }

    /**
     * {@inheritDoc}
     * Получает элемент по индексу.
     *
     * @throws NoSuchElementException если индекс некорректен
     */
    @Override
    public T get(int index){
        Node<T> cNode = getNode(index);
        if (cNode == null){
            throw new NoSuchElementException();
        }
        return cNode.value;
    }

    /**
     * {@inheritDoc}
     * Возвращает значение последнего элемента списка.
     *
     * @throws NoSuchElementException если список пуст
     */
    @Override
    public T getLast(){
        return get(size - 1);
    }

    /**
     * {@inheritDoc}
     * Возвращает значение первого элемента списка.
     *
     * @throws NoSuchElementException если список пуст
     */
    @Override
    public T getFirst(){
        return get(0);
    }

    /**
     * {@inheritDoc}
     * Удаляет элемент по индексу и корректно переставляет ссылки {@code prev}/{@code next}.
     */
    @Override
    public boolean remove(int index){
        Node<T> current = getNode(index);

        if (current != null){
            Node<T> prev = current.prev;
            Node<T> next = current.next;

            if (prev == null){
                headNode = next;
            } else {
                prev.next = next;
                current.prev = null;
            }

            if (next == null){
                tailNode = prev;
            } else {
                next.prev = prev;
                current.next = null;
            }
            size--;
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * Удаляет первое вхождение указанного значения.
     */
    @Override
    public boolean remove(T obj){
        int index = indexOff(obj);
        return index >= 0 && remove(index);
    }

    /**
     * {@inheritDoc}
     * Проверяет наличие элемента по значению.
     */
    @Override
    public boolean contains(T data) {
        return indexOff(data) != -1;
    }

    /**
     * Возвращает индекс первого вхождения элемента или {@code -1}, если элемент не найден.
     *
     * @param obj искомое значение
     * @return индекс элемента или {@code -1}
     */
    public int indexOff(T obj){
        if (obj != null){
            Node<T> node = headNode;
            int index = 0;
            while (node != null && index < size){
                if (obj.equals(node.value)){
                    return index;
                }
                node = node.next;
                index++;
            }
        }
        return -1;
    }

    /**
     * Строит строковое представление списка в прямом или обратном порядке.
     *
     * @param reverse {@code true} — обход с хвоста к голове, {@code false} — с головы к хвосту
     * @return строковое представление элементов списка в виде {@code [a, b, c]}
     */
    private String buildString(boolean reverse) {
        StringBuilder builder = new StringBuilder("[");

        Node<T> node = reverse ? tailNode : headNode;
        boolean first = true;

        while (node != null) {
            if (!first) {
                builder.append(", ");
            }
            builder.append(node.value);
            first = false;
            node = reverse ? node.prev : node.next;
        }
        builder.append("]");
        return builder.toString();
    }

    /**
     * {@inheritDoc}
     * Выводит элементы списка в порядке от головы к хвосту.
     */
    @Override
    public void display() {
        System.out.println(buildString(false));
    }

    /**
     * {@inheritDoc}
     * Выводит элементы списка в порядке от хвоста к голове.
     */
    @Override
    public void displayReverse() {
        System.out.println(buildString(true));
    }

    /**
     * Полностью разрывает ссылки между узлами и очищает голову/хвост.
     * Вызывается из {@code clear()} базового класса.
     */
    @Override
    protected void unlinkAllNodes() {
        for (Node<T> x = headNode; x != null; ) {
            Node<T> next = x.next;
            x.value = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        headNode = tailNode = null;
        size = 0;
    }

    /**
     * Возвращает итератор по элементам списка от головы к хвосту.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = headNode;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()){
                    throw new NoSuchElementException();
                }
                T value = current.value;
                current = current.next;
                return value;
            }
        };
    }

    /**
     * Возвращает строковое представление списка в прямом порядке.
     */
    @Override
    public String toString() {
        return buildString(false);
    }
}
