package tri.vo.cracktheinteview.graphtree;

import tri.vo.cracktheinteview.graphtree.ds.BinaryNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListOfDepths {

    interface ListOfDepthsCreator {

        <T> List<List<BinaryNode<T>>> createListOfDepths(BinaryNode<T> root);
    }

    static class ListOfDepthsCreatorImpl implements ListOfDepthsCreator {
        @Override
        public <T> List<List<BinaryNode<T>>> createListOfDepths(BinaryNode<T> root) {
            List<List<BinaryNode<T>>> all = new ArrayList<>();

            List<BinaryNode<T>> cur = new ArrayList<>();
            if (root != null) {
                cur.add(root);
            }

            while (!cur.isEmpty()) {
                all.add(cur);

                List<BinaryNode<T>> nextCur = new ArrayList<>();

                for (BinaryNode<T> node : cur) {
                    if (node.left != null) nextCur.add(node.left);
                    if (node.right != null) nextCur.add(node.right);
                }

                cur = nextCur;
            }

            return all;
        }
    }

    public static void main(String[] args) {
        ListOfDepthsCreator creator = new ListOfDepthsCreatorImpl();
        BinaryNode<Integer> root = BinaryNode.buildTreeFromList(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<List<BinaryNode<Integer>>> listOfDepths = creator.createListOfDepths(root);

        int i = 0;
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(1));
        expected.add(Arrays.asList(2, 3));
        expected.add(Arrays.asList(4, 5, 6));
        while (i < listOfDepths.size()) {
            List<BinaryNode<Integer>> levelINodes = listOfDepths.get(i);
            List<Integer> levelIData = levelINodes.stream().map(node -> node.data).collect(Collectors.toList());
            if (!levelIData.equals(expected.get(i))) {
                throw new AssertionError();
            }
            i++;
        }
    }
}
