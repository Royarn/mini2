package com.royarn.mini.multiThread.block;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-19
 */
public class Demo {

    public static void main(String[] args) throws InterruptedException {
        //System.out.println("执行阻塞.......");
        //waitting2();
        //System.out.println("never execute");
        sync();
    }

    /**
     * test instance block
     * @throws InterruptedException
     */
    synchronized void waitting() throws InterruptedException {
        wait();
    }

    /**
     * test instance block
     * @throws InterruptedException
     */
    static synchronized void waitting2() throws InterruptedException {
        Demo.class.wait();
    }

    /**
     * wax and matic
     */
    public static void sync() {
        Car car = new Car();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new BufferTask(car));
        service.execute(new WaxTask(car));
    }
}

class Car {
    private boolean waxOn = false;

    public synchronized void waxed() {
        waxOn = true;
        notifyAll();
    }
    public synchronized void buffed() {
        waxOn = false;
        notifyAll();
    }
    public synchronized void waitForWaxing() throws InterruptedException {
        while (waxOn == false) wait();
    }
    public synchronized void waitForBuffed() throws InterruptedException {
        while (waxOn == true) wait();
    }
}

class WaxTask implements Runnable {
    private Car car;
    public WaxTask(Car car) { this.car = car; }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("start to wax car");
                car.waxed();
                car.waitForBuffed();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class BufferTask implements Runnable {
    private Car car;
    public BufferTask(Car car) { this.car = car; }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("start to buffer car");
                car.waitForWaxing();
                car.buffed();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}