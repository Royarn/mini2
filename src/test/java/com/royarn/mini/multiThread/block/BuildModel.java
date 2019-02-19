package com.royarn.mini.multiThread.block;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-19
 */
public class BuildModel {

    public static void main(String[] args) throws InterruptedException {
        pc();
    }

    /**
     * test blocking queue
     */
    public static void pc() throws InterruptedException {
        ToaseQueue dryQueue = new ToaseQueue(),
                butterQueue = new ToaseQueue(),
                finishQueue = new ToaseQueue();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new Toaster(dryQueue));
        service.execute(new Butter(dryQueue, butterQueue));
        service.execute(new Jammer(butterQueue, finishQueue));
        service.execute(new Eater(finishQueue));
        TimeUnit.SECONDS.sleep(5);
        service.shutdown();
    }
}
class Toast {
    public enum Status { DRY, BUTTERD, JAMMED }
    private Status status = Status.DRY;
    private final int id;
    public Toast(int id) { this.id = id; }
    public void butter() { status = Status.BUTTERD; }
    public void jamme() { status = Status.JAMMED; }
    public Status getStatus() { return status; }
    public int getId() { return id; }

    @Override
    public String toString() {
        return "Toast " + id + ": " + status;
    }
}
class ToaseQueue extends LinkedBlockingQueue<Toast> {}
class Toaster implements Runnable {
    private ToaseQueue queue;
    private int count = 0;
    private Random random = new Random(47);
    public Toaster(ToaseQueue queue) { this.queue = queue; }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(100+random.nextInt(500));
                Toast t = new Toast(count++);
                System.out.println(t);
                queue.put(t);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Toaster off");
    }
}
class Butter implements Runnable {
    private ToaseQueue dryQueue, butterQueue;
    public Butter(ToaseQueue dryQueue, ToaseQueue butterQueue) {
        this.dryQueue = dryQueue;
        this.butterQueue = butterQueue;
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = dryQueue.take();
                t.butter();
                System.out.println(t);
                butterQueue.put(t);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Butter off");
    }
}
class Jammer implements Runnable {
    private ToaseQueue butterQueue, finishQueue;
    public Jammer(ToaseQueue butterQueue, ToaseQueue finishQueue) {
        this.butterQueue = butterQueue;
        this.finishQueue = finishQueue;
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = butterQueue.take();
                t.jamme();
                finishQueue.put(t);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Jammer Off");
    }
}
class Eater implements Runnable {
    private ToaseQueue finishQueue;
    private int count = 0;
    public Eater(ToaseQueue finishQueue) { this.finishQueue = finishQueue; }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = finishQueue.take();
                if (t.getId() != count++ || t.getStatus() != Toast.Status.JAMMED) {
                    System.out.println(">>>> Error" + t);
                    System.exit(1);
                } else {
                    System.out.println("Chomp! " + t);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Eater off");
    }
}