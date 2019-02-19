package com.royarn.mini.multiThread;


import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-15
 */
public class LiftOff {

    public static void main(String[] args) {
        //manual();
        //exec();
        //execResult();
        //priority();
        //thread();
        selfManage();
        System.out.println("everything is ready");
    }

    /**
     * original threads
     */
    public static void manual() {
        for (int i=0;i<5;i++) {
            new Thread(new Task01()).start();
        }
    }

    /**
     * executor
     */
    public static void exec() {
        ExecutorService service = Executors.newCachedThreadPool();
        //concurrent 串行化
        ExecutorService service1 = Executors.newSingleThreadExecutor();
        for (int i =0;i<5;i++) {
            //service.execute(new Task01());
            service1.execute(new Task01());
        }
        //service.shutdown();
        service1.shutdown();
    }

    /**
     * executor : get the result
     */
    public static void execResult() {
        ExecutorService service = Executors.newCachedThreadPool();
        ArrayList<Future<Pojo>> futures = new ArrayList<>();
        for (int i=0;i<5;i++) {
            futures.add(service.submit(new Task02()));
        }
        for (Future<Pojo> future : futures) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                service.shutdown();
            }
        }
    }

    /**
     * priority
     */
    public static void priority() {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i=0;i<5;i++) {
            service.execute(new Task03(Thread.MIN_PRIORITY));
            service.execute(new Task03(Thread.MAX_PRIORITY));
        }
        service.shutdown();
    }

    /**
     * extends Thread
     */
    public static void thread() {
        for (int i=0;i<5;i++) {
            new Task04();
        }
    }

    /**
     * self-manage
     */
    public static void selfManage() {
        for (int i=0;i<5;i++) {
            new Task05();
        }
    }
}

class Task01 implements Runnable {

    private int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;

    public Task01() {}

    public Task01(int countDown) {
        this.countDown = countDown;
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.println(status());
            Thread.yield();
        }
    }

    private String status() {
        return "#" + id + "(" + (countDown > 0 ? countDown : "LiftOff!" ) + ").";
    }
}

class Task02 implements Callable<Pojo> {

    public Task02() {}

    @Override
    public Pojo call() throws Exception {
        Random random = new Random(50);
        Pojo pojo = new Pojo();
        pojo.setId(random.nextInt(10));
        pojo.setName("royarn");
        return pojo;
    }
}

class Task03 implements Runnable {

    private int countDown = 5;
    private int priority;
    private volatile double d;

    public Task03(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return Thread.currentThread() + ": " + countDown;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);
        while (true) {
            for (int i=0;i<10000;i++) {
                d += (Math.PI + Math.E)/i;
                if (i % 1000 == 0) {
                    Thread.yield();
                }
            }
            System.out.println(this);
            if (--countDown == 0) return;
        }
    }
}

class Task04 extends Thread {

    private int countDown = 10;
    private static int threadCount = 0;

    public Task04() {
        super(Integer.toString(++threadCount));
        this.start();
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(this);
            if (--countDown == 0) {
                return;
            }
        }
    }

    @Override
    public String toString() {
        return "#" + getName() + " (" + countDown + ")";
    }
}

class Task05 implements Runnable {

    private int countDown = 5;
    private Thread t;
    public Task05() {
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(this);
            if (--countDown ==0) return;
        }
    }

    @Override
    public String toString() {
        return "#" + Thread.currentThread().getName() + " (" + countDown + ")";
    }
}

class Pojo {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{ id: " + id + ", name: " + name + " }";
    }
}