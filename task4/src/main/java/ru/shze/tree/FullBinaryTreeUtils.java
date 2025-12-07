package ru.shze.tree;

/**
 * Утилитный класс для проверки свойств полного бинарного дерева.
 * Полное дерево: каждый узел имеет либо 0, либо 2 потомка.
 */
public class FullBinaryTreeUtils extends TreeUtils {

    /**
     * Проверяет, что переданное дерево является полным.
     */
    public boolean isFull(TreeNode root) {
        if (root == null) return true; // пустое дерево считаем полным

        // лист
        if (root.left == null && root.right == null) {
            return true;
        }

        // у узла два потомка
        if (root.left != null && root.right != null) {
            return isFull(root.left) && isFull(root.right);
        }

        // в остальных случаях у узла только один потомок -> не полное
        return false;
    }

    /**
     * Пример ручного построения полного дерева высоты минимум 3.
     */
    public TreeNode buildSampleFullTree() {
        // высота 3: уровни 1..3
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        return root;
    }
}
