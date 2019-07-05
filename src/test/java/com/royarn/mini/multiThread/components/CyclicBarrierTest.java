package com.royarn.mini.multiThread.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-20
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        test();
    }

    /**
     * test CyclicBarrier
     */
    public static void test() {
        int nhorses = 7;
        int pause = 200;
        new HorseRace(nhorses, pause);
    }
}
class Horse implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private int strides = 0;
    private static Random random = new Random(47);
    private static CyclicBarrier b;
    public Horse(CyclicBarrier barrier) { b = barrier; }
    public synchronized int getStrides() { return strides; }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    strides += random.nextInt(3);
                }
                b.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String toString() {
        return "Horse " + id + " ";
    }
    public String tracks() {
        StringBuffer buffer = new StringBuffer();
        for (int i=0;i<getStrides();i++) {
            buffer.append("*");
        }
        buffer.append(id);
        return buffer.toString();
    }
}
class HorseRace {
    static final int finish_line = 75;
    private List<Horse> horses = new ArrayList<>();
    private ExecutorService service = Executors.newCachedThreadPool();
    private CyclicBarrier barrier;
    public HorseRace(int nhorses, final int pause) {
        barrier = new CyclicBarrier(nhorses, () -> {
           StringBuilder stringBuilder = new StringBuilder();
           for (int i=0;i<finish_line;i++) {
               stringBuilder.append("=");
           }
            System.out.println(stringBuilder.toString());
           for (Horse horse : horses)
               System.out.println(horse.tracks());
           for (Horse horse: horses) {
               if (horse.getStrides() >= finish_line) {
                   System.out.println(horse + " won");
                   service.shutdown();
                   return;
               }
           }
           try {
               TimeUnit.MILLISECONDS.sleep(pause);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
        });
        for (int i=0;i<nhorses;i++) {
            Horse horse = new Horse(barrier);
            horses.add(horse);
            service.execute(horse);
        }
    }
}