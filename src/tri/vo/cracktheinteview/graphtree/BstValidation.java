package tri.vo.cracktheinteview.graphtree;

import com.sun.xml.internal.ws.policy.spi.AssertionCreationException;
import tri.vo.cracktheinteview.graphtree.ds.BinaryNode;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BstValidation {

    boolean isBst(BinaryNode<Integer> root) {
        return isBstRecur(root, null, null);
    }

    private boolean isBstRecur(BinaryNode<Integer> root, Integer lower, Integer upper) {
        if (root == null) {
            return true;
        }

        if (lower != null && root.data < lower) {
            return false;
        }

        if (upper != null && root.data > upper) {
            return false;
        }

        return isBstRecur(root.left, lower, root.data)
                && isBstRecur(root.right, root.data, upper);
    }

    public static void main(String[] args) {
        BstValidation bstValidation = new BstValidation();
        test(bstValidation, Arrays.asList(2, 1, 3), true);
        test(bstValidation, Arrays.asList(5, 3, 8, 2, 4, 6, 9), true);
        test(bstValidation, Arrays.asList(10, 5, 15, null, null, 6, 20), false);
        test(bstValidation, Arrays.asList(1, 2, 3), false);
        test(bstValidation, Arrays.asList(3, 2, 5, 1, null, 4, 6), true);
        test(bstValidation, Arrays.asList(8, 3, 10, 1, 6, null, 14, null, null, 4, 7, 13), true);
        test(bstValidation, Arrays.asList(8, 3, 10, 1, 6, 5, 14, null, null, 4, 7, 13), false);
        test(bstValidation, Collections.emptyList(), true);
        test(bstValidation, Collections.singletonList(3), true);
        test(bstValidation, Arrays.asList(5, 1, 7, null, 6), false);
    }

    private static void test(BstValidation bstValidation, List<Integer> data, boolean expected) {
        BinaryNode<Integer> node = BinaryNode.buildTreeFromList(data);
        boolean result = bstValidation.isBst(node);
        if (result != expected) {
            throw new AssertionError();
        }
    }
}
