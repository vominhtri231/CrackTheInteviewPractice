package tri.vo.cracktheinteview.graphtree;

import tri.vo.cracktheinteview.graphtree.ds.BinaryNodeWithParent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FirstCommonAncestor {

    BinaryNodeWithParent<String> findFirstCommonAncestor(
            BinaryNodeWithParent<String> a, BinaryNodeWithParent<String> b) {

        Set<BinaryNodeWithParent<String>> aAncestors = new HashSet<>();

        while (a.parent != null) {
            aAncestors.add(a.parent);
            a = a.parent;
        }

        while (b != null && !aAncestors.contains(b)) {
            b = b.parent;
        }

        if (b == null) {
            throw new RuntimeException("No common ancestor found");
        }

        return b;
    }

    public static void main(String[] args) {
        BinaryNodeWithParent<String> root = BinaryNodeWithParent.buildTreeFromList(
                Arrays.asList(
                        "1",
                        "2", "3",
                        "4", "5", "6", "7",
                        null, null, "8", "9", null, null, "10", null,
                        null, null, "11", "12", null, null
                )
        );

        BinaryNodeWithParent<String> fourNode = root.left.left;
        BinaryNodeWithParent<String> twelveNode = root.left.right.right.right;
        BinaryNodeWithParent<String> twoNode = root.left;

        BinaryNodeWithParent<String> commonNode =
                new FirstCommonAncestor().findFirstCommonAncestor(fourNode, twelveNode);

        if (twoNode != commonNode) {
            throw new AssertionError();
        }
    }
}
