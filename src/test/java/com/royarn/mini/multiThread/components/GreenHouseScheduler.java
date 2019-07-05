package com.royarn.mini.multiThread.components;

import java.util.Calendar;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-21
 */
public class GreenHouseScheduler {
    private volatile boolean light = false;
    private volatile boolean water = false;
    private String thermostst = "Day";
    public synchronized String getThermostst() {
        return thermostst;
    }
    public synchronized void setThermostst(String thermostst) {
        this.thermostst = thermostst;
    }
    ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(10);
    public void scheduled(Runnable event, long delay) {
        scheduled.schedule(event, delay, TimeUnit.MILLISECONDS);
    }
    public void repeat(Runnable event, long initalDelay, long period) {
        scheduled.scheduleAtFixedRate(event, initalDelay, period, TimeUnit.MILLISECONDS);
    }
    class LightOn implements Runnable {
        @Override
        public void run() {
            System.out.println("Turning on lights");
            light = true;
        }
    }
    class LightOff implements Runnable {
        @Override
        public void run() {
            System.out.println("Turning off lights");
            light = false;
        }
    }
    class WateOn implements Runnable {
        @Override
        public void run() {
            System.out.println("Turning greenhouse water on");
            water = true;
        }
    }
    class WateOff implements Runnable {
        @Override
        public void run() {
            System.out.println("Turning greenhouse water off");
            water = false;
        }
    }
    class ThermostsNight implements Runnable {
        @Override
        public void run() {
            System.out.println("Themosts to night setting");
            setThermostst("Night");
        }
    }
    class ThermostsDay implements Runnable {
        @Override
        public void run() {
            System.out.println("Themosts to day setting");
            setThermostst("Day");
        }
    }
    class Bell implements Runnable {
        @Override
        public void run() {
            System.out.println("Terminating");
            scheduled.shutdownNow();
            new Thread(() -> {
                //for ()
            }).start();
        }
    }
    static class DataPoint {
        final Calendar time;
        final float temperature;
        final float humidity;
        public DataPoint(Calendar calendar, float temp, float hum) {
            time = calendar;
            temperature = temp;
            humidity = hum;
        }
    }
}

class Sequence {
    private int id;
    private CountDownLatch latch;
    public Sequence(CountDownLatch latch) {
        this.latch = latch;
    }
    public boolean getId() {
        if (id >= 100000000) return false;
        id++;
        return true;
    }

    public static void main(String[] args) throws InterruptedException {
        /*CountDownLatch latch = new CountDownLatch(8);
        Sequence sequence = new Sequence(latch);
        long start = System.nanoTime();
        for (int i=0;i<8;i++) {
            new Thread(() -> {
                try {
                    while (true) {
                        if (!sequence.getId()) {
                            return;
                        }
                    }
                } finally {
                    latch.countDown();
                }
            }).start();
        }
        latch.await();
        long cost = System.nanoTime() - start;
        System.out.println("耗时： " + TimeUnit.NANOSECONDS.toSeconds(cost));*/
        //lock();
        visiable();
    }

    /**
     * test reetray
     */
    public static synchronized void lock() {
        System.out.println("第一层锁: " + Sequence.class);
        lock2();
    }

    public synchronized static void lock2() {
        System.out.println("第二层锁: " + Sequence.class);
    }

    /**
     * test visiable
     */
    public static void visiable() throws InterruptedException {
        class Tem {
            private String name;
            private CountDownLatch latch;

            public Tem(String name, CountDownLatch latch) {
                this.name = name;
                this.latch = latch;
            }

            @Override
            public String toString() {
                return "Value: " + name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
        CountDownLatch latch = new CountDownLatch(2);
        Tem tem = new Tem("Initial", latch);
        new Thread(() -> {
            System.out.println("write thread");
            tem.setName("Write");
            latch.countDown();
        }).start();
        new Thread(() -> {
            System.out.println("read thread");
            latch.countDown();
        }).start();
        latch.await();
        System.out.println(tem);
    }
}