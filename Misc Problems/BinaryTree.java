import java.util.Scanner;

public class BinaryTree {

    private Node root;

    public void add(Scanner s){
        this.root = add(s, root);

    }

    public boolean isBST(){
        return isBST(root, null, null);
    }

    private boolean isBST(Node node, Integer start, Integer end) {

        if (node == null){
            return true;
        }

        boolean present = true;

        if (start != null && start.compareTo(node.value) >= 0){
            present = false;
        }
        if (end != null && end.compareTo(node.value) <= 0){
            present = false;
        }

        boolean left = isBST(node.left, start, node.value);
        boolean right = isBST(node.right, node.value, end);

        return present && left && right;
    }


    private Node add(Scanner s, Node node) {

        if (node == null){
            System.out.println("vaue to set ");
            int value = Integer.parseInt(s.nextLine());
            return new Node(value);
        }


        System.out.println("Enter Left or Right of " + node.value);
        String direction = s.nextLine();

        if (direction.equals("left")){
            node.left = add(s, node.left);
        } else{
            node.right = add(s, node.right);
        }
        return node;
    }

    public boolean structurallyIdentical(BinaryTree other) {
        return this.structurallyIdentical(this.root, other.root);
    }


    private boolean structurallyIdentical(Node tnode, Node onode) {
        // write your code here
        if (tnode.value == 0){
            return true;
        }

        boolean present = tnode.value == onode.value;
        boolean left = structurallyIdentical(tnode.left, onode.left);
        boolean right = structurallyIdentical(tnode.right, onode.right);

        return present && left && right;
    }


    public int height(){

        return height(root);
    }

    private int height(Node node) {

        if (node == null){
            return 0;
        }

        return 1 + Math.max(height(node.left), height(node.right));
    }

    public int diameter(){
        return diameter(root);
    }

    private int diameter(Node node) {

        if (node == null){
            return 0;
        }

        int current = 1 + height(node.left) + height(node.right);
        int max = Math.max(diameter(node.left),diameter(node.right));

        return Math.max(current,max);

    }
    public int max(){
        return max(root);
    }

    private int max(Node node) {

        if (node == null){
            return Integer.MIN_VALUE;
        }

        int current = node.value;
        int max = Math.max(max(node.left),max(node.right));

        return Math.max(current,max);

    }

    public boolean find(int target){
        return find(root, target);
    }

    private boolean find(Node node, int target) {

        if (node == null){
            return false;
        }

        boolean current = node.value == target;
        boolean left = find(node.left,target);
        boolean right = find(node.right,target);

        if (current || left || right){
            return true;
        }

        return false;
    }

    private void mirror(Node node){

        if (node == null){
            return;
        }

        Node temp = node.left;
        node.left = node.right;
        node.right = temp;

        mirror(node.left);
        mirror(node.right);

    }

    //ALL ORDERS

    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null){
            return;
        }

    }


    private class Node{
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

}
