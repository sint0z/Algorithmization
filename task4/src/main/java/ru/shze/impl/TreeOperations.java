package ru.shze.impl;

/**
 * Интерфейс базовых операций изменения дерева.
 */
public interface TreeOperations {

    /**
     * Вставка нового значения в дерево.
     */
    void insert(int data);

    /**
     * Удаление узла по ключу с использованием замены
     * на самый глубокий правый узел.
     */
    void delete(int key);
}