package com.royarn.mini.multiThread.block;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-19
 */
public class Demo {

    public static void main(String[] args) throws InterruptedException, IOException {
        //System.out.println("执行阻塞.......");
        //waitting2();
        //System.out.println("never execute");
        //sync();
        pipeIO();
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

    /**
     * I/O
     */
    public static void pipeIO() throws IOException {
        Sender sender = new Sender();
        Receiver receiver = new Receiver(sender);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(sender);
        service.execute(receiver);
        service.shutdown();
    }

    /**
     * death lock
     */
    public static void lock() {
        int ponder = 5;
        int size = 5;
        ExecutorService service = Executors.newCachedThreadPool();
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

class Sender implements Runnable {
    private Random random = new Random(47);
    private PipedWriter writer = new PipedWriter();
    public PipedWriter getWriter() { return writer; }
    @Override
    public void run() {
        try {
            while (true) {
                for (char c = 'A';c<='z';c++) {
                    writer.write(c);
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class Receiver implements Runnable {
    private PipedReader reader;
    public Receiver(Sender sender) throws IOException { this.reader = new PipedReader(sender.getWriter());  }
    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Read: " + (char)reader.read());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}