package com.royarn.mini.datastruct;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-12
 */
public class LinkedList<T> {

    /**
     * Node
     * @param data
     */
    private Node node;

    private int size;

    public LinkedList() {
        this.node = new Node(null, null);
    }

    public void add(T data) {
        this.node = new Node(data, this.node);
        size++;
    }

    public boolean hasNext() {
        return this.node.next != null;
    }

    public int size() {
        return size;
    }


    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        while (list.hasNext()) {
            System.out.println(list.node);
            list.node = list.node.next;
        }
    }
}

/**
 * 定义数据结构
 */
class Node<T> {
    /**
     * 自身夹带数据
     */
    T data;

    /**
     * 指向下一个节点
     */
    Node next;

    public Node(T data, Node next) {
        this.data = data;
        this.next = next;
    }

    @Override
    public String toString() {
        return "{ data: " + data + "}";
    }
}