package ru.shze.tree;

import ru.shze.impl.TreeOperations;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Реализация бинарного дерева со стратегией вставки по уровням (level-order).
 * Вставка идёт в первую свободную позицию при обходе в ширину.
 * Удаление заменяет найденный ключ самым глубоким правым узлом.
 */
public class BinaryTree extends TreeUtils implements TreeOperations {

    private TreeNode root;

    public TreeNode getRoot() {
        return root;
    }

    @Override
    public void insert(int data) {
        TreeNode newNode = new TreeNode(data);
        if (root == null) {
            root = newNode;
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            if (current.left == null) {
                current.left = newNode;
                break;
            } else {
                queue.offer(current.left);
            }

            if (current.right == null) {
                current.right = newNode;
                break;
            } else {
                queue.offer(current.right);
            }
        }
    }

    @Override
    public void delete(int key) {
        if (root == null) {
            return;
        }

        // Если дерево состоит из одного узла
        if (root.left == null && root.right == null) {
            if (root.data == key) {
                root = null;
            }
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        TreeNode keyNode = null;
        TreeNode current = null;
        TreeNode parentOfDeepest = null;

        // Поиск узла с заданным ключом и самого глубокого правого узла
        while (!queue.isEmpty()) {
            current = queue.poll();

            if (current.data == key) {
                keyNode = current;
            }
            if (current.left != null) {
                parentOfDeepest = current;
                queue.offer(current.left);
            }
            if (current.right != null) {
                parentOfDeepest = current;
                queue.offer(current.right);
            }
        }

        // current сейчас указывает на самый глубокий посещённый узел
        TreeNode deepest = current;

        if (keyNode != null) {
            // заменяем значение
            keyNode.data = deepest.data;

            // удаляем самый глубокий узел из дерева
            if (parentOfDeepest != null) {
                if (parentOfDeepest.right == deepest) {
                    parentOfDeepest.right = null;
                } else if (parentOfDeepest.left == deepest) {
                    parentOfDeepest.left = null;
                }
            } else {
                // если deepest == root (редкий случай)
                root = null;
            }
        }
    }
}
