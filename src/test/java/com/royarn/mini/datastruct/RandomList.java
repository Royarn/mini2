package com.royarn.mini.datastruct;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomList<E> {

    private List<E> storage = new ArrayList<>();
    private Random random = new Random(47);

    public void add(E element) {
        storage.add(element);
    }

    public E select() {
        return storage.get(random.nextInt(storage.size()));
    }

    public static void main(String[] args) {
        RandomList<String> randomList = new RandomList<>();
        for (String s : "The quick brown fox jumped over the lazy brown dog".split(" "))
            randomList.add(s);
        for (int i=0;i<11;i++) {
            System.out.println(randomList.select() + " ");
        }
    }
}