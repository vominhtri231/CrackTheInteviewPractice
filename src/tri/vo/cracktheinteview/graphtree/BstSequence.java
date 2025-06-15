package tri.vo.cracktheinteview.graphtree;

import tri.vo.cracktheinteview.graphtree.ds.BstNode;

import java.util.*;

public class BstSequence {

    void printBstSequence(BstNode<Integer> root) {
        Set<Integer> eles = extractElements(root);

        printBstSequenceRecursive(eles, new LinkedList<>(), null, root);
    }

    private void printBstSequenceRecursive(
            Set<Integer> eles, LinkedList<Integer> res,
            BstNode<Integer> cur, BstNode<Integer> root) {
        if (eles.isEmpty()) {
            System.out.println(res);
            return;
        }

        Set<Integer> elesCopy = new HashSet<>(eles);

        for (Integer ele : elesCopy) {
            if (isValid(cur, root, ele)) {
                res.add(ele);
                eles.remove(ele);

                if (cur == null) {
                    cur = new BstNode<>(ele);
                } else {
                    cur.add(ele);
                }

                printBstSequenceRecursive(eles, res, cur, root);

                eles.add(ele);
                cur = cur.removeLeaf(ele);
                res.removeLast();
            }
        }
    }

    private boolean isValid(BstNode<Integer> cur, BstNode<Integer> root, Integer ele) {
        if (root == null) {
            return false;
        }

        if (cur == null) {
            return ele.equals(root.data);
        }

        if (cur.data.compareTo(ele) > 0) {
            if (cur.left == null) {
                return Objects.equals(root.left.data, ele);
            } else {
                return isValid(cur.left, root.left, ele);
            }
        } else {
            if (cur.right == null) {
                return Objects.equals(root.right.data, ele);
            } else {
                return isValid(cur.right, root.right, ele);
            }
        }
    }

    private Set<Integer> extractElements(BstNode<Integer> root) {
        if (root == null) {
            return Collections.emptySet();
        }

        Set<Integer> res = new HashSet<>();
        res.add(root.data);
        res.addAll(extractElements(root.left));
        res.addAll(extractElements(root.right));
        return res;
    }

    public static void main(String[] args) {
        BstNode<Integer> integerBstNode = BstNode.buildTreeFromList(Arrays.asList(3, 2, 4, 1));

        new BstSequence().printBstSequence(integerBstNode);
    }
}
