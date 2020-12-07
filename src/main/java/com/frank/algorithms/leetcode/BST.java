package com.frank.algorithms.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: algorithms
 * @description:
 * @author: Francis-Tmac
 * @create: 2020-12-07
 **/

public class BST {

    private Node root;

    private int count;

    private Node insert(Node root, int key, String value){
        return insert(null,root,key,value);
    }

    private Node insert(Node parent, Node root, int key, String value){
        if ( root == null){
            count++;
            return new Node(parent, key,value);
        }
        if(root.getKey() == key){
            root.setValue(value);

        } else if ( root.getKey() > key){
            root.setLeftNode(insert(root,root.getLeftNode(), key, value));
        } else{
            root.setRightNode(insert(root,root.getRightNode(), key, value));
        }
        return root;
    }
    private boolean contain(Node root, int key){
        if(root == null){
            return false;
        }

        if(root.getKey() == key){
            return true;
        }else if(root.getKey() > key){
            return contain(root.getLeftNode(),key);
        }else {
            return contain(root.getRightNode(),key);
        }
    }

    private Node search(Node root, int key){
        if(root == null){
            return null;
        }

        if(root.getKey() == key){
            return root;
        }else if(root.getKey() > key){
            return search(root.getLeftNode(),key);
        }else {
            return search(root.getRightNode(),key);
        }
    }

    /***
     * 前序遍历
     * @param root
     */
    private void preOrder(Node root){
        if(root != null){
            System.out.println("key:" + root.getKey() + "--value:" + root.getValue());
        }
        if(root.getLeftNode() != null){
            preOrder(root.getLeftNode());
        }
        if(root.getRightNode() != null){
            preOrder(root.getRightNode());
        }
    }

    /***
     * 后续遍历
     * @param root
     */
    private void postOrder(Node root){

        if(root.getLeftNode() != null){
            postOrder(root.getLeftNode());
        }
        if(root.getRightNode() != null){
            postOrder(root.getRightNode());
        }
        if(root != null){
            System.out.println("key:" + root.getKey() + "--value:" + root.getValue());
        }
    }

    private Node getMinNode(){
        if(count == 0){
            return null;
        }
        return minNode(root);
    }

    private Node minNode(Node node){
        if(node.getLeftNode() == null){
            return node;
        }else {
            return minNode(node.getLeftNode());
        }
    }

    private Node getMaxNode(){
        if(count == 0){
            return null;
        }
        return maxNode(root);
    }

    private Node maxNode(Node node){
        if(node.getRightNode() == null){
            return node;
        }else {
            return maxNode(node.getRightNode());
        }
    }

    private void destroy(Node root){
        if(root.getLeftNode() != null){
            destroy(root.getLeftNode());
        }
        if(root.getRightNode() != null){
            destroy(root.getRightNode());
        }
        if(root != null){
            System.out.println("delete key:" + root.getKey() + "--value:" + root.getValue());
            root.setNull();
            count--;
        }
    }

    private Node removeMin(Node root){
        if(root.getLeftNode() == null){
            Node min = root;

        }
        return null;
    }

    private void levelOrder(Node root){
        Queue<Node> queue = new LinkedList();
        if(root != null){
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if(node == root){
                System.out.println(node.getRootString());
            }else {
                System.out.println(node.getString());
            }

            if(node.getLeftNode() != null){
                queue.add(node.getLeftNode());
            }
            if(node.getRightNode() != null){
                queue.add(node.getRightNode());
            }
        }
    }



    public static void main(String[] args) {
        BST bst = new BST();
        Node root = bst.insert(null, 8, "baba");
        bst.root = root;
        bst.insert(root, 6, "liuliu");
        bst.insert(root, 3, "sansan");
        bst.insert(root, 7, "qiqi");
        bst.insert(root, 13, "shisan");
        bst.insert(root, 10, "shi");
        bst.insert(root, 12, "shier");
        bst.insert(root, 14, "shisi");
        bst.insert(root, 5, "wuwu");
//        System.out.println("preOrder: ");
//        bst.preOrder(root);
        System.out.println("levelOrder: ");
        bst.levelOrder(root);

        System.out.println("minNode: " + bst.getMinNode().getString());

        System.out.println("maxNode: " + bst.getMaxNode().getString());
    }
}

class Node{
    private int key;

    private String value;

    private Node parent;

    private Node leftNode;

    private Node rightNode;

    public Node(Node parent, int key, String value){
        this.parent = parent;
        this.key = key;
        this.value = value;
    }

    public void setNull(){
        this.key = 0;
        this.value = null;
        this.leftNode = null;
        this.rightNode = null;
    }

    public int getKey(){
        return key;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public String getValue(){
        return value;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", value='" + value + '\'' +
                ", parent=" + parent +
                ", leftNode=" + leftNode +
                ", rightNode=" + rightNode +
                '}';
    }

    public String getString(){
        return "Node{" +
                "key=" + key +
                ", value='" + value +
                ", parent.key =" + parent.getKey() +
                '}';
    }
    public String getRootString(){
        return "Node{" +
                "key=" + key +
                ", value='" + value +
                '}';
    }
}
