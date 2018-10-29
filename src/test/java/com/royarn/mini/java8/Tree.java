package com.royarn.mini.java8;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-24
 */

/**
 * define tree constructor
 */
public class Tree {

    /**
     * this node has the data
     */
    private String data;

    /**
     * the children
     */
    private Tree left, right;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Tree getLeft() {
        return left;
    }

    public void setLeft(Tree left) {
        this.left = left;
    }

    public Tree getRight() {
        return right;
    }

    public void setRight(Tree right) {
        this.right = right;
    }

    /**
     * build add API
     */
}
