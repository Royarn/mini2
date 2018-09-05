package com.royarn.mini.java8;

public class LamdaDemo2 {

    public static void process(Runnable runnable) {
        runnable.run();
    }

    public static void main(String[] args) {
        Runnable runnable1 = () -> System.out.println("Thread-1 print");
        Runnable runnable2 = () -> System.out.println("Thread-2 print");
        process(runnable1);
        process(runnable2);
    }
}