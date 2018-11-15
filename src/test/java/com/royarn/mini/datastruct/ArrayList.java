package com.royarn.mini.datastruct;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-12
 */
public class ArrayList<T> {

    private Object[] elements;

    private int index;

    public ArrayList(int size) {
        elements = new Object[size];
    }

    public void add(T data) {
        elements[index] = data;
        index++;
    }

    public int length() {
        return index;
    }

    public T get(int index) {
        return (T) elements[index];
    }

    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>(10);
        arrayList.add("blink");
        arrayList.add("shadow");
        arrayList.add("flink");
        for (int i = 0;i<arrayList.length();i++) {
            System.out.println(arrayList.get(i));
        }
    }
}