package com.royarn.mini.datastruct;

public class LinkStack2<T> {

    private static class Node<U> {
        U item;
        Node<U> next;
        Node() {
            item = null;
            next = null;
        }
        Node(U item, Node<U> next) {
            this.next = next;
            this.item = item;
        }
        boolean end() {
            return item == null && next == null;
        }
    }

    private Node<T> top = new Node<>();

    public void push(T item) {
        top = new Node<>(item, top);
    }

    public T pop() {
        T result = top.item;
        if (!top.end()) {
            top = top.next;
        }
        return result;
    }

    public static void main(String[] args) {
        LinkStack2<String> linkStack2 = new LinkStack2<>();
        for (String s : "Phrase on stun!".split(" "))
            linkStack2.push(s);
        String s;
        while ((s = linkStack2.pop()) != null) {
            System.out.println(s);
        }
    }
}
