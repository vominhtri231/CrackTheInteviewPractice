package tri.vo.cracktheinteview.graphtree;

import tri.vo.cracktheinteview.graphtree.ds.BinaryNodeWithParent;

import java.util.Arrays;

public class Successor {

    BinaryNodeWithParent<Integer> nextInOrder(BinaryNodeWithParent<Integer> cur) {
        if (cur == null) {
            return null;
        }
        if (cur.right != null) {
            return leftMost(cur.right);
        }
        return parentOnLeft(cur);
    }

    private BinaryNodeWithParent<Integer> parentOnLeft(BinaryNodeWithParent<Integer> cur) {
        BinaryNodeWithParent<Integer> parent = cur.parent;
        if (parent == null) {
            return null;
        }

        if (parent.left == cur) {
            return parent;
        }

        return parentOnLeft(parent);
    }

    private BinaryNodeWithParent<Integer> leftMost(BinaryNodeWithParent<Integer> cur) {
        if (cur.left != null) {
            return leftMost(cur.left);
        }
        return cur;
    }

    public static void main(String[] args) {
        BinaryNodeWithParent<Integer> root = BinaryNodeWithParent.buildTreeFromList(
                Arrays.asList(4, 2, 6, 1, 3, 5, 7)
        );

        Successor successor = new Successor();

        BinaryNodeWithParent<Integer> node1 = root.left.left;   // 1
        BinaryNodeWithParent<Integer> node2 = root.left;        // 2
        BinaryNodeWithParent<Integer> node3 = root.left.right;  // 3
        BinaryNodeWithParent<Integer> node4 = root;             // 4
        BinaryNodeWithParent<Integer> node5 = root.right.left;  // 5
        BinaryNodeWithParent<Integer> node6 = root.right;       // 6
        BinaryNodeWithParent<Integer> node7 = root.right.right; // 7

        if (successor.nextInOrder(node1).data != 2) throw new AssertionError();
        if (successor.nextInOrder(node2).data != 3) throw new AssertionError();
        if (successor.nextInOrder(node3).data != 4) throw new AssertionError();
        if (successor.nextInOrder(node4).data != 5) throw new AssertionError();
        if (successor.nextInOrder(node5).data != 6) throw new AssertionError();
        if (successor.nextInOrder(node6).data != 7) throw new AssertionError();
        if (successor.nextInOrder(node7) != null) throw new AssertionError();
    }
}
