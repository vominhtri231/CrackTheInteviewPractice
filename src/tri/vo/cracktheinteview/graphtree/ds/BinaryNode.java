package tri.vo.cracktheinteview.graphtree.ds;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryNode<T> {
    public T data;
    public BinaryNode<T> left;
    public BinaryNode<T> right;

    public BinaryNode(T data) {
        this(data, null, null);
    }

    public BinaryNode(T data, BinaryNode<T> left, BinaryNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public static <T> BinaryNode<T> buildTreeFromList(List<T> dataList) {
        if (dataList == null || dataList.isEmpty()) {
            return null;
        }

        Queue<BinaryNode<T>> queue = new LinkedList<>();
        BinaryNode<T> root = new BinaryNode<>(dataList.get(0));
        queue.add(root);

        int i = 1;
        while (i < dataList.size()) {
            BinaryNode<T> current = queue.poll();

            if (current == null) {
                break;
            }

            if (i < dataList.size()) {
                T leftData = dataList.get(i++);
                if (leftData != null) {
                    current.left = new BinaryNode<>(leftData);
                    queue.add(current.left);
                }
            }

            if (i < dataList.size()) {
                T rightData = dataList.get(i++);
                if (rightData != null) {
                    current.right = new BinaryNode<>(rightData);
                    queue.add(current.right);
                }
            }
        }

        return root;
    }
}
