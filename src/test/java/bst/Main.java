package bst;

public class Main {
    public static void main(String[] args) {
        BinarSearchTree myBST=new BinarSearchTree();
        System.out.println(myBST.insert(11));
        System.out.println(myBST.insert(22));
        System.out.println(myBST.insert(1));
        System.out.println(myBST.root.value);
        System.out.println(myBST.root.left.value);
        System.out.println(myBST.root.right.value);
        System.out.println(myBST.contains(11));
        System.out.println(myBST.contains(111));
        System.out.println(myBST.contains(22));
        System.out.println(myBST.contains(1));
        System.out.println(myBST.contains(0));
    }
}
