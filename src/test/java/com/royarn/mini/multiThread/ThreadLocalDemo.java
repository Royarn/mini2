package com.royarn.mini.multiThread;

import java.util.Random;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-18
 */
public class ThreadLocalDemo {
}

class Accessor implements Runnable {

    private final int id;
    public Accessor(int id) { this.id = id ; }
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {

        }
    }
}

class LocalVar {
    private static ThreadLocal<Integer> val = new ThreadLocal<Integer>() {
        private Random random = new Random(47);
        protected synchronized Integer initValue() {
            return random.nextInt(10000);
        }
    };
    public static void increment() {
    }
}
