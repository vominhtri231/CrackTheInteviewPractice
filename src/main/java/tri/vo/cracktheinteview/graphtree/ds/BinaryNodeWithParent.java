package tri.vo.cracktheinteview.graphtree.ds;


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryNodeWithParent<T> {
    public T data;
    public BinaryNodeWithParent<T> left;
    public BinaryNodeWithParent<T> right;
    public BinaryNodeWithParent<T> parent;

    public BinaryNodeWithParent(T data) {
        this(data, null, null, null);
    }

    public BinaryNodeWithParent(T data, BinaryNodeWithParent<T> left, BinaryNodeWithParent<T> right, BinaryNodeWithParent<T> parent) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public static <T> BinaryNodeWithParent<T> buildTreeFromList(List<T> dataList) {
        if (dataList == null || dataList.isEmpty()) {
            return null;
        }

        Queue<BinaryNodeWithParent<T>> queue = new LinkedList<>();
        BinaryNodeWithParent<T> root = new BinaryNodeWithParent<>(dataList.get(0));
        queue.add(root);

        int i = 1;
        while (i < dataList.size()) {
            BinaryNodeWithParent<T> current = queue.poll();

            if (current == null) {
                break;
            }

            if (i < dataList.size()) {
                T leftData = dataList.get(i++);
                if (leftData != null) {
                    current.left = new BinaryNodeWithParent<>(leftData);
                    current.left.parent = current;
                    queue.add(current.left);
                }
            }

            if (i < dataList.size()) {
                T rightData = dataList.get(i++);
                if (rightData != null) {
                    current.right = new BinaryNodeWithParent<>(rightData);
                    current.right.parent = current;
                    queue.add(current.right);
                }
            }
        }

        return root;
    }
}

