package com.royarn.mini.multiThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-18
 */
public class DealShareResDemo {

    public static void main(String[] args) {
        //lock();
        //lock2();
        //sync();
        sync2();
    }

    /**
     * ReentrantLock
     */
    public static void lock() {
        ResControl res = new ResControl();
        res.untime();
        res.timed();
        new Thread() {
            { setDaemon(true); }

            @Override
            public void run() {
                System.out.println("acquired");
            }
        }.start();
        res.untime();
        res.timed();
    }

    /**
     * test lock
     */
    public static void lock2() {
        ExecutorService service = Executors.newCachedThreadPool();
        Res res = new Res();
        for (int i=0;i<100;i++) {
            service.execute(res);
        }
        service.shutdown();
    }

    /**
     * sync
     */
    public static void sync() {
        class AtimicTest implements Runnable {
            private int i =0;
            public int getValue() { return i; }
            private synchronized void increse() { i++; i++; }
            @Override
            public void run() {
                while (true) increse();
            }
        }
        ExecutorService service = Executors.newCachedThreadPool();
        AtimicTest at = new AtimicTest();
        service.execute(at);
        while (true) {
            int val = at.getValue();
            if (val%2 != 0) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }

    /**
     * sync
     */
    public static void sync2() {
        DualSync sync = new DualSync();
        new Thread(() -> sync.f()).start();
        sync.g();
    }
}

abstract class IntGenerator {
    private volatile boolean canceled = false;
    abstract int next();
    public void cancel() { canceled = true; }

    public boolean isCanceled() {
        return canceled;
    }
}

class EvenChecker implements Runnable {
    private IntGenerator generator;
    private final int id;
    public EvenChecker(IntGenerator g, int id) {
        generator = g;
        this.id = id;
    }

    @Override
    public void run() {
        while (!generator.isCanceled()) {
            int val = generator.next();
            if (val%2 != 0) {
                System.out.println(val + " not even！");
                generator.cancel();
            }
        }
    }
}

class ResControl {
    private ReentrantLock lock = new ReentrantLock();
    public void untime() {
        boolean capture = lock.tryLock();
        try {
            System.out.println("captured: " + capture);
        } finally {
            if (capture) { lock.unlock(); }
        }
    }
    public void timed() {
        boolean capture = false;
        try {
            capture = lock.tryLock(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("get lock failed");
        }
        try {
            System.out.println("capture: " + capture);
        } finally {
            if (capture) { lock.unlock(); }
        }
    }
}

class Res implements Runnable {

    private int num;
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " start execute and currentValue is: " + num);
            num++;
        } finally {
            lock.unlock();
        }
    }
}

class DualSync {
    private Object syncObject = new Object();
    public synchronized void f() {
        for (int i=0;i<5;i++) {
            System.out.println("f()");
            Thread.yield();
        }
    }
    public void g() {
        synchronized (syncObject) {
            for (int i=0;i<5;i++) {
                System.out.println("g()");
                Thread.yield();
            }
        }
    }
}