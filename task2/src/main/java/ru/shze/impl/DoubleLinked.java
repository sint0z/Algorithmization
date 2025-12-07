// Данная работа выполнена Стоцким Н.А., Ник (s1ntoz)
// GitHub: https://github.com/sint0z
package ru.shze.impl;

/**
 * Интерфейс двусвязного списка, расширяющий базовый функционал {@link SingleLinked}
 * операциями доступа и модификации по индексу, а также обходом в обратном порядке.
 * Поддерживает перебор элементов в цикле {@code for-each} благодаря наследованию
 * от {@link Iterable}.
 *
 * @param <E> тип элементов списка
 */
public interface DoubleLinked<E> extends SingleLinked<E>, Iterable<E> {

    /**
     * Вставляет элемент в список по указанному индексу.
     * Допустимые значения индекса: от {@code 0} до {@code size()} включительно.
     * При вставке по индексу {@code size()} элемент добавляется в конец списка.
     *
     * @param index позиция, на которую нужно вставить элемент
     * @param data  вставляемый элемент
     * @throws IndexOutOfBoundsException если индекс вне диапазона {@code [0, size()]}
     */
    boolean add(int index, E data);

    /**
     * Удаляет элемент по указанному индексу и возвращает его значение.
     *
     * @param index индекс удаляемого элемента
     * @return значение удалённого элемента
     * @throws IndexOutOfBoundsException если индекс вне диапазона {@code [0, size() - 1]}
     */
    boolean remove(int index);

    /**
     * Возвращает значение элемента по указанному индексу,
     * не изменяя структуру списка.
     *
     * @param index индекс элемента
     * @return значение элемента на указанной позиции
     * @throws IndexOutOfBoundsException если индекс вне диапазона {@code [0, size() - 1]}
     */
    E get(int index);

    /**
     * Выводит элементы списка в обратном порядке.
     * Конкретный формат вывода определяется реализацией.
     */
    void displayReverse();

    /**
     * Возвращает значение первого элемента списка.
     *
     * @return значение первого элемента
     * @throws java.util.NoSuchElementException если список пуст
     */
    E getFirst();

    /**
     * Возвращает значение последнего элемента списка.
     *
     * @return значение последнего элемента
     * @throws java.util.NoSuchElementException если список пуст
     */
    E getLast();
}