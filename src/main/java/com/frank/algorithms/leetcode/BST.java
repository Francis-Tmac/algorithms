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

    public int floor(int key){

        if( count == 0 || key < 0 ){
            return -1;
        }

        Node floorNode = floor(root, key);
        return floorNode.getKey();
    }


    private Node floor(Node node, int key){
        if(node == null) {
            return null;
        }
        //如果该node的key和key相等，就是本身
        if(node.getKey() == key){
            return node;
        }

        //如果该node比key要大的话
        if(node.getKey() > key){
            return floor(node.getLeftNode(), key);
        }

        //如果node比key小，可能是，也能是不是
        Node tempNode = floor(node.getRightNode(), key);
        if(tempNode != null) {
            return tempNode;
        }
        //想当于 tempNode == null
        return node;
    }

    private Node removeMin(){
        if(root != null){
            return removeMin(root);
        }
        return null;
    }

    private Node removeMax(){
        if(root != null){
            return removeMax(root);
        }
        return null;
    }


    private Node removeMin(Node root){
        // 当左子节点为空时即该节点为，最小节点。将右子节点返回
        if(root.getLeftNode() == null){
            Node right = root.getRightNode();
            count --;
            return right;
        }
        // 若有左子节点则继续遍历，左子节点
        root.setLeftNode(removeMin(root.getLeftNode()));
        return root;
    }

    private Node removeMax(Node root){
        if(root.getRightNode() == null){
            Node left = root.getLeftNode();
            count --;
            return left;
        }
        root.setRightNode(removeMax(root.getRightNode()));
        return root;
    }

    private void remove(int key){
        remove(root,key);
    }

    /****
     * 删除以 root 为根的二分搜索树中键值为 key 的节点
     * 返回删除节点后新的二分搜索树中的根节点
     * @param root
     * @param key
     * @return
     */
    private Node remove(Node root, int key){
        // 包含了寻找键值key 的过程
        if(root == null){
            return null;
        }
        if(key > root.getKey()){
            // 在右节点中把对应的 key 删除
            // 将删除后的子树根节点返回
            root.setRightNode(remove(root.getRightNode(),key));
            return root;
        }else if (key < root.getKey()){
            root.setLeftNode(remove(root.getLeftNode(), key));
            return root;
        }else {
            if(root.getLeftNode() == null){
                return root.getRightNode();
            }else if(root.getRightNode() == null){
                return root.getLeftNode();
            }{
               if( root.getLeftNode() == null){
                   Node rightNode = root.getRightNode();
                   root = null;
                   count --;
                   return rightNode;
               } else if(root.getRightNode() == null){
                   Node leftNode = root.getLeftNode();
                   root = null;
                   count --;
                   return leftNode;
               } else{
                    Node successor = minNode(root.getRightNode());
                    successor.setRightNode(removeMin(root.getRightNode()));
                    successor.setLeftNode(root.getLeftNode());
                    root = null;
                    return successor;
               }
            }
        }
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
        bst.insert(root, 15, "shiwu");
//        System.out.println("preOrder: ");
//        bst.preOrder(root);
        System.out.println("levelOrder: ");
        bst.levelOrder(root);

        System.out.println(bst.floor(4));

//        Node newRoot = bst.remove(root,8);

//        System.out.println("minNode: " + bst.getMinNode().getString());
//
//        System.out.println("maxNode: " + bst.getMaxNode().getString());


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

//    @Override
//    public String toString() {
//        return "Node{" +
//                "key=" + key +
//                ", value='" + value + '\'' +
//                ", parent=" + parent +
//                ", leftNode=" + leftNode +
//                ", rightNode=" + rightNode +
//                '}';
//    }

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
