package com.royarn.mini.multiThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-18
 */
public class JoinThread {

    public static void main(String[] args) throws InterruptedException {
        //join();
        //join2();
        thr();
    }

    /**
     * test join()
     */
    public static void join() throws InterruptedException {
        Thread t1 = new Thread(() -> System.out.println("this is first thread"));
        Thread t2 = new Thread(() -> System.out.println("this is second thread"));
        t1.start();
        t2.start();
        t2.join();
    }

    /**
     * test join()
     */
    public static void join2() {
        Sleeper sleepy = new Sleeper("Sleepy", 1500),
                grumy = new Sleeper("Grumpy", 1500);
        new Joiner("Dopey", sleepy);
        new Joiner("Doc", grumy);
        grumy.interrupt();
    }

    /**
     * exception thread
     */
    public static void thr() {
        class ExceptionThread extends Thread {
            @Override
            public void run() {
                throw new RuntimeException("线程异常");
            }
        }
        try {
            ExecutorService service = Executors.newCachedThreadPool();
            service.execute(new ExceptionThread());
        } catch (Exception e) {
            System.out.println("Exception has been handled");
        }
    }
}

class Sleeper extends Thread {
    private int duration;
    public Sleeper(String name, int sleepTime) {
        super(name);
        duration = sleepTime;
        start();
    }
    @Override
    public void run() {
        try {
            sleep(duration);
        } catch (InterruptedException e) {
           // System.out.println(getName() + " was interrupted " + "isInterrupted: " + isInterrupted());
            e.printStackTrace();
        }
        System.out.println(getName() + " has awakened");
    }
}

class Joiner extends Thread {
    private Sleeper sleeper;
    public Joiner(String name, Sleeper sleeper) {
        super(name);
        this.sleeper = sleeper;
        start();
    }

    @Override
    public void run() {
        try {
            sleeper.join();
        } catch (InterruptedException e) {
            System.out.println(getName() + " join completed");
        }
    }
}

class ExeThread2 implements Runnable {
    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("run by " + t);
        System.out.println("eh: " + t.getUncaughtExceptionHandler());
        throw new RuntimeException();
    }
}

class MyUncaughtEx implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught " + e);
    }
}

class HandlerThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        System.out.println(this + " creating new Thread");
        Thread t = new Thread(r);
        System.out.println("created " + t);
        t.setUncaughtExceptionHandler(new MyUncaughtEx());
        return t;
    }
}