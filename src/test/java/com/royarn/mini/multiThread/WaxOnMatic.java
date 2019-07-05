package com.royarn.mini.multiThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author lizhiqiang
 * @date 2019-06-06
 */
public class WaxOnMatic {

    public static void main(String[] args) throws InterruptedException {
        Car car = new Car();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new WaxOn(car));
        service.execute(new PolishOn(car));
        TimeUnit.SECONDS.sleep(10);
        service.shutdown();
    }
}

class Car {
    private boolean waxOn = false;
    //涂蜡
    public synchronized void waxed() {
        waxOn = true;
        this.notifyAll();
    }
    //抛光
    public synchronized void polished() {
        waxOn = false;
        this.notifyAll();
    }

    public synchronized void waitForWaxing() throws InterruptedException {
        while (waxOn == false)
            this.wait();
    }

    public synchronized void waitForPolishing() throws InterruptedException {
        while (waxOn == true)
            this.wait();
    }
}

class WaxOn implements Runnable {
    private Car car;
    public WaxOn(Car car) {
        this.car = car;
    }
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                System.out.println("Wax on");
                TimeUnit.SECONDS.sleep(3);
                car.waxed();
                car.waitForPolishing();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }
        }
    }
}

class PolishOn implements Runnable {
    private Car car;
    public PolishOn(Car car) {
        this.car = car;
    }
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                System.out.println("Polishing On");
                TimeUnit.SECONDS.sleep(1);
                car.polished();
                car.waitForWaxing();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {}
        }
    }
}