package ru.shze.impl;

import ru.shze.tree.TreeNode;

/**
 * Базовый интерфейс для любого бинарного дерева с целыми значениями.
 * Определяет общие операции обхода и получения высоты.
 */
public interface TreeTraversal {

    /**
     * Прямой обход (pre-order): сначала узел, затем левое и правое поддерево.
     */
    void preOrder(TreeNode root);

    /**
     * Центрированный обход (in-order): левое поддерево, узел, правое поддерево.
     */
    void inOrder(TreeNode root);

    /**
     * Обратный обход (post-order): левое поддерево, правое поддерево, узел.
     */
    void postOrder(TreeNode root);

    /**
     * Поуровневый обход (level-order, обход в ширину).
     */
    void levelOrder(TreeNode root);

    /**
     * Вычисляет высоту дерева (количество уровней).
     * Пустое дерево имеет высоту 0.
     */
    int height(TreeNode root);
}


