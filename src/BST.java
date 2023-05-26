import java.util.ArrayList;
import java.util.List;

public class BST <K extends Comparable<K>, V> {
    private Node root;
    private class Node{
        private K key;
        private V val;
        private Node left, right;
        public Node(K key, V val){
            this.key = key;
            this.val = val;
        }
    }
    public void put(K key, V val) {
        if (root == null) {
            root = new Node(key, val);//compares to a current key (root at first)
            return;
        }

        Node current = root;
        Node parent;

        while (true) {
            parent = current;
            int cmp = key.compareTo(current.key);

            if (cmp < 0) { //its less than root so it goes to the left
                current = current.left;
                if (current == null) {
                    parent.left = new Node(key, val);//if there is no elem, creates new left child
                    return;
                }
            } else if (cmp > 0) { //if its more than root, it goes on the right
                current = current.right;
                if (current == null) {
                    parent.right = new Node(key, val); //if there is no elem, creates new right child
                    return;
                }
            } else {
                current.val = val; //if they are equal nothing changes
        }
        }
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return (node != null) ? node.val : null;
    }


    private Node getNode(Node node, K key) {
        if (node == null || key.equals(node.key)) { //case where needed value is first
            return node;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return getNode(node.left, key);//if needed elem is less than root, it will search on the left side
        } else {
            return getNode(node.right, key);//if needed elem is more or equal than root, it will search on the right side
        }
    }

    public void delete(K key) {
        root = deleteNode(root, key);
    }

    private Node deleteNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);//search as in previous methods
        if (cmp < 0) {
            node.left = deleteNode(node.left, key);
        } else if (cmp > 0) {
            node.right = deleteNode(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node successor = findMinNode(node.right);//finds a minimum child so that a greater will become parent
                node.key = successor.key;
                node.val = successor.val;
                node.right = deleteNode(node.right, successor.key);
            }
        }

        return node;
    }

    private Node findMinNode(Node node) {
        while (node.left != null) {//min value is the most left one
            node = node.left;
        }
        return node;
    }
    public Iterable<K> iterator() {
        List<K> keys = new ArrayList<>();//cretaes a keys list
        inorderTraversal(root, keys);
        return keys;
    }

    private void inorderTraversal(Node node, List<K> keys) {
        if (node != null) {
            inorderTraversal(node.left, keys);//adds elems to keys list starting from the most left
            keys.add(node.key);
            inorderTraversal(node.right, keys);//then adds right ones
        }
    }
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return size(node.left) + 1 + size(node.right);//recursively counts every element
    }
    public int findLevel(K key, V value) {
        if (root == null) {
            return -1;
        }

        return findLevelHelper(root, key, value, 0);
    }

    private int findLevelHelper(Node node, K key, V value, int level) {
        if (node == null) {
            return -1; // Key-value pair not found
        }

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            return findLevelHelper(node.left, key, value, level + 1);
        } else if (cmp > 0) {
            return findLevelHelper(node.right, key, value, level + 1);
        } else {
            if (value.equals(node.val)) {
                return level; // Key-value pair found
            } else {
                return -1; // Key found, but value does not match
            }
        }
    }
    public void contains(V value, K key){
        System.out.println(containsHelper(root, value, key));
    }
    public boolean containsHelper(Node node, V value, K key){
        if(node == null){
            return false;
        }
        int cmp = key.compareTo(node.key);
        if(value == root.val){
            return true;
        } else if(cmp < 0) {
            containsHelper(node.left, value, key);
        } else if (cmp > 0){
            containsHelper(node.right, value, key);
        }
        return false;
    }

        }