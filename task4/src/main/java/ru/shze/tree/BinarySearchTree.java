package ru.shze.tree;

/**
 * Реализация бинарного дерева поиска.
 * Для каждого узла: все элементы слева меньше, справа больше или равны.
 */
public class BinarySearchTree extends TreeUtils {

    private TreeNode root;

    public TreeNode getRoot() {
        return root;
    }

    /**
     * Вставка элемента в BST.
     */
    public void insert(int data) {
        root = insertRecursive(root, data);
    }

    private TreeNode insertRecursive(TreeNode current, int data) {
        if (current == null) {
            return new TreeNode(data);
        }
        if (data < current.data) {
            current.left = insertRecursive(current.left, data);
        } else {
            current.right = insertRecursive(current.right, data);
        }
        return current;
    }

    /**
     * Построение BST из последовательности целых чисел.
     */
    public void buildFromArray(int[] values) {
        for (int value : values) {
            insert(value);
        }
    }
}
