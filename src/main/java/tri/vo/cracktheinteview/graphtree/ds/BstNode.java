package tri.vo.cracktheinteview.graphtree.ds;

import java.util.List;

public class BstNode<T extends Comparable<T>> {

    public T data;
    public BstNode<T> left;
    public BstNode<T> right;

    public BstNode(T data) {
        this(data, null, null);
    }

    public BstNode(T data, BstNode<T> left, BstNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public void add(T value) {
        if (data.compareTo(value) > 0) {
            if (left == null) {
                left = new BstNode<>(value);
            } else {
                left.add(value);
            }
        } else {
            if (right == null) {
                right = new BstNode<>(value);
            } else {
                right.add(value);
            }
        }
    }

    public BstNode<T> removeLeaf(T value) {
        BstNode<T> container = new BstNode<>(value);

        container.left = this;
        this.removeLeafRecursively(container, value);
        return container.left;
    }

    private void removeLeafRecursively(BstNode<T> parent, T value) {
        if (data.compareTo(value) > 0) {
            if (left == null) {
                throw new IllegalArgumentException("Not found value");
            }

            left.removeLeafRecursively(this, value);
            return;
        }

        if (data.compareTo(value) < 0) {
            if (right == null) {
                throw new IllegalArgumentException("Not found value");
            }

            right.removeLeafRecursively(this, value);
            return;
        }

        if (left != null || right != null) {
            throw new IllegalArgumentException("Found value is not leaf node");
        }
        if (parent.left == this) {
            parent.left = null;
        } else {
            parent.right = null;
        }
    }

    public static <V extends Comparable<V>> BstNode<V> buildTreeFromList(List<V> dataList) {
        BstNode<V> cur = null;
        for(V item : dataList) {
            if (cur == null) {
                cur = new BstNode<>(item);
            } else {
                cur.add(item);
            }
        }
        return cur;
    }
}
