package ru.shze.tree;

/**
 * Узел бинарного дерева.
 * Хранит целое значение и ссылки на левого и правого потомка.
 */
public class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    public TreeNode(int data) {
        this.data = data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }
}
