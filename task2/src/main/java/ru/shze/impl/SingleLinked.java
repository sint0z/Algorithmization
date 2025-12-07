// Данная работа выполнена Стоцким Н.А., Ник (s1ntoz)
// GitHub: https://github.com/sint0z
package ru.shze.impl;

import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * Односвязный список с базовыми операциями добавления, удаления и поиска.
 * Поддерживает перебор элементов в цикле {@code for-each} благодаря
 * наследованию от {@link Iterable}.
 *
 * @param <E> тип элементов списка
 */
public interface SingleLinked<E> extends Iterable<E>  {


    boolean addAll(Collection<? extends E> collection);

    /**
     * Добавляет элемент в начало списка.
     *
     * @param data добавляемый элемент
     */
    void addFirst(E data);

    /**
     * Добавляет элемент в конец списка.
     *
     * @param data добавляемый элемент
     */
    void addLast(E data);

    /**
     * Удаляет первый элемент списка.
     *
     * @return значение удалённого элемента
     * @throws NoSuchElementException если список пуст
     */
    E removeFirst();

    /**
     * Удаляет последний элемент списка.
     *
     * @return значение удалённого элемента
     * @throws NoSuchElementException если список пуст
     */
    E removeLast();

    /**
     * Удаляет первое вхождение указанного значения из списка.
     *
     * @param data значение для удаления
     * @return {@code true}, если элемент был найден и удалён,
     *         {@code false} в противном случае
     */
    boolean remove(E data);

    /**
     * Проверяет, содержится ли значение в списке.
     *
     * @param data искомое значение
     * @return {@code true}, если список содержит указанный элемент,
     *         {@code false} иначе
     */
    boolean contains(E data);

    /**
     * Возвращает количество элементов в списке.
     *
     * @return размер списка
     */
    int size();

    /**
     * Проверяет, пуст ли список.
     *
     * @return {@code true}, если список не содержит элементов,
     *         {@code false} иначе
     */
    boolean isEmpty();

    /**
     * Выводит элементы списка в удобочитаемом виде.
     * Конкретный формат вывода определяется реализацией.
     */
    void display();

    /**
     * Удаляет все элементы из списка.
     * После вызова размер списка становится равен нулю.
     */
    void clear();

    /**
     * Маркерный интерфейс для узла списка.
     * Конкретная структура узла определяется реализацией.
     *
     * @param <E> тип данных, хранимых в узле
     */
    interface Node<E> { }
}
