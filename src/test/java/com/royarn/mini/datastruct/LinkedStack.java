package com.royarn.mini.datastruct;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/7 14:38
 */
public class LinkedStack<T> {

    /**
     * a dataNode
     * @param <E>
     */
    static class Node<E> {
        E e;
        Node<E> next;

        Node() {e = null; next = null;}

        Node(E e, Node<E> next) {
            this.e = e;
            this.next = next;
        }

        boolean end() {return e == null && next == null;}
    }

    private Node<T> top = new Node<T>();
    public void push() {

    }
}