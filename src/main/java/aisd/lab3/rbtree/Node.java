package aisd.lab3.rbtree;

import static aisd.lab3.rbtree.Color.RED;
import java.util.Objects;

public class Node<K extends Comparable<K>, V> {

    private K key;
    private V value;
    private Node<K, V> left, right;
    private Color color;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.color = RED;
    }

    public boolean isRed() {
        return RED.equals(color);
    }

    public K getKey() {
        return key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Node<K, V> getLeft() {
        return left;
    }

    public void setLeft(Node<K, V> leftNode) {
        left = leftNode;
    }

    public Node<K, V> getRight() {
        return right;
    }

    public void setRight(Node<K, V> rightNode) {
        right = rightNode;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node<?, ?> other = (Node<?, ?>) obj;
        if (!Objects.equals(this.key, other.key)) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        if (!Objects.equals(this.left, other.left)) {
            return false;
        }
        if (!Objects.equals(this.right, other.right)) {
            return false;
        }
        return this.color == other.color;
    }

    @Override
    public int hashCode() {
        int hash = key.hashCode();
        hash = 17 * hash + value.hashCode();
        hash = 17 * hash + color.hashCode();

        if (left != null) {
            hash = 17 * hash + left.hashCode();
        }
        if (right != null) {
            hash = 17 * hash + right.hashCode();
        }

        return hash;
    }
}
