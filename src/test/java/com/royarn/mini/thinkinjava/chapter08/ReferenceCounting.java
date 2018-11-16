package com.royarn.mini.thinkinjava.chapter08;

import java.util.Arrays;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-16
 */
public class ReferenceCounting {

    public static void main(String[] args) {
        //shard data
        Shard shard = new Shard();
        Composing[] composings = {
                new Composing(shard),
                new Composing(shard),
                new Composing(shard),
                new Composing(shard)
        };
        /**Arrays.stream(composings)
                .forEach(composing -> composing.dispose());*/
        for (Composing composing : composings) {
            composing.dispose();
        }
    }
}

class Shard {
    private int refcount = 0;
    private static long counter = 0;
    private final long id = counter++;

    public Shard() {
        System.out.println("Creating " + this);
    }

    public void addRef() { counter++; }

    protected void dispose() {
        if (--refcount == 0) {
            System.out.println("Disposing " + this);
        }
    }

    @Override
    public String toString() {
        return "Shard " + refcount;
    }
}

class Composing {
    private Shard shard;
    private static long counter = 0;
    private static long id = counter++;

    public Composing(Shard shard) {
        System.out.println("Creating " + this);
        this.shard = shard;
        this.shard.addRef();
    }

    protected void dispose() {
        System.out.println("Disposing " + this);
        shard.dispose();
    }

    @Override
    public String toString() {
        return "Composing " + id;
    }
}