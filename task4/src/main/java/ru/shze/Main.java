package ru.shze;

import ru.shze.tree.*;

public class Main {
    public static void main(String[] args) {

        // 1. Обычное бинарное дерево со вставкой по уровням
        System.out.println("=== Обычное бинарное дерево (вставка по уровням) ===");
        BinaryTree levelTree = new BinaryTree();
        levelTree.insert(1);
        levelTree.insert(2);
        levelTree.insert(3);
        levelTree.insert(4);
        levelTree.insert(5);

        System.out.print("Поуровневый обход: ");
        levelTree.levelOrder(levelTree.getRoot());
        System.out.println();

        System.out.println("Высота дерева: " + levelTree.height(levelTree.getRoot()));

        System.out.println("Удаляем узел со значением 3 (замена на самый глубокий правый узел)...");
        levelTree.delete(3);

        System.out.print("Поуровневый обход после удаления: ");
        levelTree.levelOrder(levelTree.getRoot());
        System.out.println();
        System.out.println();

        // 2. Полное бинарное дерево и проверка isFull()
        System.out.println("=== Полное бинарное дерево ===");
        FullBinaryTreeUtils fullUtils = new FullBinaryTreeUtils();
        TreeNode fullRoot = fullUtils.buildSampleFullTree();

        System.out.print("Поуровневый обход: ");
        fullUtils.levelOrder(fullRoot);
        System.out.println();

        System.out.println("Высота дерева: " + fullUtils.height(fullRoot));
        System.out.println("Дерево является полным: " + fullUtils.isFull(fullRoot));
        System.out.println();

        // 3. Бинарное дерево поиска (BST) из последовательности
        System.out.println("=== Бинарное дерево поиска (BST) ===");
        BinarySearchTree bst = new BinarySearchTree();
        int[] values = {5, 2, 8, 1, 3, 7, 9};
        bst.buildFromArray(values);

        System.out.print("Центрированный обход (in-order), элементы должны быть по возрастанию: ");
        bst.inOrder(bst.getRoot());
        System.out.println();

        System.out.println("Высота BST: " + bst.height(bst.getRoot()));
        System.out.println();

        // 4. Сбалансированное дерево из отсортированного массива
        System.out.println("=== Сбалансированное дерево из отсортированного массива ===");
        BalancedTreeBuilder balancedBuilder = new BalancedTreeBuilder();
        int[] sorted = {1, 2, 3, 4, 5, 6, 7};
        TreeNode balancedRoot = balancedBuilder.buildBalancedFromSortedArray(sorted);

        System.out.print("Поуровневый обход сбалансированного дерева: ");
        balancedBuilder.levelOrder(balancedRoot);
        System.out.println();

        System.out.println("Высота сбалансированного дерева: " + balancedBuilder.height(balancedRoot));
        System.out.println("Разница высот левого и правого поддерева корня: "
                + (balancedBuilder.height(balancedRoot.getLeft()) - balancedBuilder.height(balancedRoot.getRight())));
    }
}

