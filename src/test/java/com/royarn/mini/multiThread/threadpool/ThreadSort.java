package com.royarn.mini.multiThread.threadpool;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-14
 */
public class ThreadSort {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> System.out.println("Thread-1"));
        Thread t2 = new Thread(() -> System.out.println("Thread-2"));
        Thread t3 = new Thread(() -> System.out.println("Thread-3"));
        t1.start();t1.join();
        t2.start();t2.join();
        t3.start();t3.join();
    }
}
