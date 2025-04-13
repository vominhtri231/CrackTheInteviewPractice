package tri.vo.cracktheinteview.graphtree;

import tri.vo.cracktheinteview.graphtree.ds.BinaryNode;

public class MinimalTree {

    interface MinTreeCreator {
        BinaryNode<Integer> createMinTree(int[] elements);
    }

    static class MinTreeCreatorImpl implements MinTreeCreator {

        @Override
        public BinaryNode<Integer> createMinTree(int[] elements) {
            for (int i = 1; i < elements.length; i++) {
                if (elements[i] < elements[i - 1]) {
                    throw new IllegalArgumentException("Element " + elements[i] + " is lower than element " + elements[i - 1]);
                }
            }

            return createMinTreeRecursive(elements, 0, elements.length - 1);
        }

        private BinaryNode<Integer> createMinTreeRecursive(int[] elements, int start, int end) {
            if (end < start) {
                return null;
            }

            if (start == end) {
                return new BinaryNode<>(elements[start]);
            }

            int mid = (end - start) / 2 + start;
            BinaryNode<Integer> left = createMinTreeRecursive(elements, start, mid - 1);
            BinaryNode<Integer> right = createMinTreeRecursive(elements, mid + 1, end);
            return new BinaryNode<>(elements[mid], left, right);
        }
    }


    public static void main(String[] args) {
        MinTreeCreator minTreeCreator = new MinTreeCreatorImpl();
        testMinTreeCreator(minTreeCreator, new int[]{3, 5, 7, 8, 17, 100}, 3);
        testMinTreeCreator(minTreeCreator, new int[]{3, 5, 7, 8, 9, 17, 100}, 3);
        testMinTreeCreator(minTreeCreator, new int[]{3, 5, 7, 8, 9, 11, 17, 100}, 4);
        testMinTreeCreator(minTreeCreator, new int[]{3, 5, 7, 8, 9, 11, 17, 99, 100}, 4);
        testMinTreeCreator(minTreeCreator, new int[]{}, 0);
        testMinTreeCreator(minTreeCreator, new int[]{99}, 1);
    }

    static void testMinTreeCreator(MinTreeCreator minTreeCreator, int[] input, int expectedHeight) {
        BinaryNode<Integer> converted = minTreeCreator.createMinTree(input);
        testIsBinarySearchTree(converted);
        if (getHeight(converted) != expectedHeight) {
            throw new IllegalArgumentException("Wrong height, expect:" + expectedHeight + ", got:" + getHeight(converted));
        }
    }

    static void testIsBinarySearchTree(BinaryNode<Integer> root) {
        testIsBinarySearchTreeRecursive(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    static void testIsBinarySearchTreeRecursive(BinaryNode<Integer> root, int left, int right) {
        if (root == null) {
            return;
        }

        if (root.data < left || root.data > right) {
            throw new AssertionError();
        }

        testIsBinarySearchTreeRecursive(root.left, left, root.data);
        testIsBinarySearchTreeRecursive(root.right, root.data, right);
    }

    static int getHeight(BinaryNode<Integer> root) {
        if (root == null) {
            return 0;
        }

        return Math.max(getHeight(root.left) + 1, getHeight(root.right) + 1);
    }
}
