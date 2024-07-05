package bst;

public class BinarSearchTree {
    Node root;

    class Node {
        int value;
        Node left;
        Node right;

        private Node(int value) {
            this.value = value;
        }
    }
    public void insert(){
        if(root==null) {
            Node newNod = new Node(11);
            newNod.left = null;
            newNod.right = null;
        }
    }
}
