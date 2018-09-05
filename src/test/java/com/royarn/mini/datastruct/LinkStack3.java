package com.royarn.mini.datastruct;

public class LinkStack3<E> {

    /**
     * 抽象链表内部结构   节点和节点自身存储数据类型
     */
    private static class Node<E> {
        E element;
        Node<E> next;

        /**
         * 初始化构造空节点
         */
        Node() {
            element = null;
            next = null;
        }

        Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        /**
         * 判断节点是否为空
         */
        boolean end() {
            return element == null && next == null;
        }
    }

    /**
     * 空节点
     */
    private Node<E> top = new Node<>();

    /**
     * 入栈
     */
    public void push(E element) {
        top = new Node<>(element, top);
    }

    /**
     * 出栈
     */
    public <E> E pop() {
        if (!top.end()) {
            top = top.next;
        }
        return (E) top.element;
    }

    public static void main(String[] args) {
        LinkStack3<String> linkStack3 = new LinkStack3<>();
        for (String s : "Phrase on running ...".split(" ")) linkStack3.push(s);
        String s;
        while ((s = linkStack3.pop()) != null) {
            System.out.println(s);
        }
    }
}
