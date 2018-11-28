package com.royarn.mini.thinkinjava.chapter10;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-21
 */
public class Sequence {

    private Object[] items;
    private int next = 0;

    public Sequence(int size) {
        items = new Object[size];
    }

    public void add(Object ele) {
        if (next < items.length) {
            items[next++] = ele;
        }
    }

    private class SequenceSelector implements Selector {

        private int i = 0;

        @Override
        public boolean hasNext() {
            return i != items.length;
        }

        @Override
        public Object next() {
            Object current = null;
            if (i < items.length) {
                current = items[i];
                i++;
                return current;
            }
            return current;
        }
    }

    public Selector selector() {
        return new SequenceSelector();
    }

    public static void main(String[] args) {
        Sequence sequence = new Sequence(10);
        for (int i=0; i<10;i++) {
            sequence.add(Integer.toString(i));
        }
        Selector selector = sequence.selector();
        while (selector.hasNext()) {
            System.out.println(selector.next());
        }
    }
}

interface Selector {
    boolean hasNext();
    Object next();
}