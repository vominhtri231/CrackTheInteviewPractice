package tri.vo.cracktheinteview.graphtree;

import tri.vo.cracktheinteview.graphtree.ds.BinaryNode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CheckBalanced {

    boolean isBalanced(BinaryNode<Integer> root) {
        return checkBalancedRecursive(root) >= 0;
    }

    /**
     * Return height if node balance, or -1 if not
     */
    private int checkBalancedRecursive(BinaryNode<Integer> root) {
        if (root == null) {
            return 0;
        }

        int leftRecursive = checkBalancedRecursive(root.left);
        int rightRecursive = checkBalancedRecursive(root.right);

        if (leftRecursive < 0 || rightRecursive < 0) {
            return -1;
        }

        if (Math.abs(leftRecursive - rightRecursive) > 1) {
            return -1;
        }

        return Math.max(leftRecursive, rightRecursive) + 1;
    }

    public static void main(String[] args) {
        CheckBalanced checkBalanced = new CheckBalanced();

        test(checkBalanced, Arrays.asList(1, 2, 3, 4, 5, 6, 7), true);
        test(checkBalanced, Arrays.asList(1, 2, null, 3), false);
        test(checkBalanced, Arrays.asList(1, 2, 3, 4), true);
        test(checkBalanced, Collections.emptyList(), true);
        test(checkBalanced, Collections.singletonList(1), true);
        test(checkBalanced, Arrays.asList(1, 2, null, 3, null, null, null, 4), false);
    }

    private static void test(CheckBalanced checkBalanced, List<Integer> value, boolean expected) {
        BinaryNode<Integer> caseData = BinaryNode.buildTreeFromList(value);
        boolean caseResult = checkBalanced.isBalanced(caseData);
        if (caseResult != expected) {
            throw new AssertionError();
        }
    }
}
