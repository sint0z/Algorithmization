// Данная работа выполнена Стоцким Н.А., Ник (s1ntoz)
// GitHub: https://github.com/sint0z
package ru.shze.linkedlist;

import ru.shze.impl.SingleLinked;

import java.util.Collection;

/**
 * Абстрактная базовая реализация связного списка,
 * предоставляющая общую логику работы с размером коллекции.
 * Конкретные реализации (односвязный, двусвязный, циклический) отвечают
 * за хранение узлов и корректное обновление поля {@link #size}
 * при добавлении и удалении элементов.
 *
 * @param <E> тип элементов списка
 */
public abstract class AbstractLinkedList<E> implements SingleLinked<E> {
    public AbstractLinkedList(){}

    public AbstractLinkedList(Collection<? extends E> collection){
        addAll(collection);
    }

    /**
     * Текущее количество элементов в списке.
     */
    protected int size;

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Должен обнулить все ссылки на узлы (head, tail, next/prev и т.д.).
     */
    protected abstract void unlinkAllNodes();

    /**
     * {@inheritDoc}
     * Базовая реализация сбрасывает размер в ноль.
     * Конкретная реализация списка должна дополнительно
     * обнулить все ссылки на узлы, чтобы избежать утечки памяти.
     */
    @Override
    public void clear() {
        unlinkAllNodes();
        size = 0;
    }


}
