package aisd.lab3.rbtree;

import static aisd.lab3.rbtree.Color.BLACK;
import static aisd.lab3.rbtree.Color.RED;

public class RedBlackTree<K extends Comparable<K>, V> {

    private Node<K, V> root;

    public void put(K key, V value) {
        validateParams(key, value);
        root = put(root, key, value);
        root.setColor(BLACK);
    }

    public Node<K, V> getRoot() {
        return root;
    }

    private void validateParams(K key, V value) {
        if (key == null | value == null) {
            throw new IllegalArgumentException("Input params (key, value) cannot be null.");
        }
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null) {
            return new Node(key, value);
        }

        if (isKeyBiggerThanNode(key, node)) {
            putOnTheRight(node, key, value);

        } else if (isKeySmallerThanNode(key, node)) {
            putOnTheLeft(node, key, value);

        } else {
            node.setValue(value);
        }

        return reorganizeTree(node);
    }

    private boolean isKeyBiggerThanNode(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void putOnTheRight(Node<K, V> node, K key, V value) {
        node.setRight(put(node.getRight(), key, value));
    }

    private boolean isKeySmallerThanNode(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private void putOnTheLeft(Node<K, V> node, K key, V value) {
        node.setLeft(put(node.getLeft(), key, value));
    }

    private Node<K, V> reorganizeTree(Node<K, V> node) {
        node = rotateLeftIfNeeded(node);
        node = rotateRightIfNeeded(node);
        changeColorsIfNeeded(node);
        return node;
    }

    private Node<K, V> rotateLeftIfNeeded(Node<K, V> node) {
        if (isBlack(node.getLeft()) && isRed(node.getRight())) {
            node = rotateLeft(node);
        }
        return node;
    }

    private Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> rightChild = node.getRight();

        if (hasLeftChild(rightChild)) {
            node.setRight(rightChild.getLeft());
        } else {
            node.setRight(null);
        }

        if (node.equals(root)) {
            root = rightChild;
        } else if (isRightChild(node)) {
            Node<K, V> parent = findParent(node);
            parent.setRight(rightChild);
        } else {
            Node<K, V> parent = findParent(node);
            parent.setLeft(rightChild);
        }

        rightChild.setLeft(node);

        Color tmp = rightChild.getColor();
        rightChild.setColor(node.getColor());
        node.setColor(tmp);

        return rightChild;
    }

    private boolean isRightChild(Node<K, V> node) {

        Node<K, V> parent = findParent(node);

        return hasRightChild(parent) && parent.getRight().equals(node);
    }

    private Node<K, V> findParent(Node<K, V> node) {
        Node<K, V> iterator = root;

        while (!iterator.getLeft().equals(node) && !iterator.getRight().equals(node)) {
            if (isKeyBiggerThanNode(iterator.getKey(), node)) {
                iterator = iterator.getLeft();
            } else {
                iterator = iterator.getRight();
            }
        }

        return iterator;
    }

    private boolean hasLeftChild(Node<K, V> node) {
        return node.getLeft() != null;
    }

    private Node<K, V> rotateRightIfNeeded(Node<K, V> node) {
        if (isRed(node.getLeft()) && isRed(node.getLeft().getLeft())) {
            node = rotateRight(node);
        }
        return node;
    }

    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> leftChild = node.getLeft();

        if (hasRightChild(leftChild)) {
            node.setLeft(leftChild.getRight());
        } else {
            node.setLeft(null);
        }

        if (node.equals(root)) {
            root = leftChild;
        } else if (isRightChild(node)) {
            Node<K, V> parent = findParent(node);
            parent.setRight(leftChild);
        } else {
            Node<K, V> parent = findParent(node);
            parent.setLeft(leftChild);
        }

        leftChild.setRight(node);

        Color tmp = leftChild.getColor();
        leftChild.setColor(node.getColor());
        node.setColor(tmp);

        return leftChild;
    }

    private boolean hasRightChild(Node<K, V> node) {
        return node.getRight() != null;
    }

    private void changeColorsIfNeeded(Node<K, V> node) {
        if (isRed(node.getLeft()) && isRed(node.getRight())) {
            changeColors(node);
        }
    }

    private void changeColors(Node<K, V> node) {
        node.setColor(RED);
        node.getLeft().setColor(BLACK);
        node.getRight().setColor(BLACK);
    }

    private boolean isBlack(Node<K, V> node) {
        return !isRed(node);
    }

    private boolean isRed(Node<K, V> node) {
        return node == null
                ? false
                : node.isRed();
    }
}
