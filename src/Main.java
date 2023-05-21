public class Main {
    public static void main(String[] args) {
        BST binary = new BST();//created a new binarySearchTree
        binary.put(1, 8);
        binary.put(2, 4);
        binary.put(4, 10);
        binary.put(3, 2);//added elems to bst
        System.out.println("value from key 1: " + binary.get(1)); //get value from key 1
        System.out.println("keys iterated: " + binary.iterator()); //iterate keys
        System.out.println("size: " + binary.size());//get size of bst

    }

}