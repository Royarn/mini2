package com.royarn.mini.multiThread;

/**
 * Description:
 *
 * @author dell
 * @date 2019-01-30
 */
public class VolatileTest {

    private volatile boolean stop = false;

    public void doWork() {
        while (!stop) {
        }
        System.out.println("条件改变，执行.........");
    }

    public void shutDown() {
        stop = true;
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileTest volatileTest = new VolatileTest();
        new Thread(() -> volatileTest.doWork()).start();
        Thread.sleep(2000);
        new Thread(() -> volatileTest.shutDown()).start();
    }
}
