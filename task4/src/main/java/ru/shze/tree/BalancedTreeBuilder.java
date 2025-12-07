package ru.shze.tree;

/**
 * Построение сбалансированного бинарного дерева поиска из отсортированного массива.
 * Используется стратегия: середина массива становится корнем, для подмассивов рекурсивно.
 */
public class BalancedTreeBuilder extends TreeUtils {

    /**
     * Строит сбалансированное BST из отсортированного по возрастанию массива.
     * Разница высот левого и правого поддерева каждого узла не превышает 1.
     */
    public TreeNode buildBalancedFromSortedArray(int[] sorted) {
        return buildBalanced(sorted, 0, sorted.length - 1);
    }

    private TreeNode buildBalanced(int[] arr, int left, int right) {
        if (left > right) return null;
        int mid = (left + right) / 2;
        TreeNode node = new TreeNode(arr[mid]);
        node.left = buildBalanced(arr, left, mid - 1);
        node.right = buildBalanced(arr, mid + 1, right);
        return node;
    }
}
