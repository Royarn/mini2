package com.royarn.mini.benkchac;


import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

abstract class Accumulator {

    public static long cycles = 5000l;
    private static final int N = 4;
    public static ExecutorService executor = Executors.newFixedThreadPool(N * 2);
    private static CyclicBarrier barrier = new CyclicBarrier(N * 2 + 1);
    protected volatile int index = 0;
    protected volatile long value = 0;
    protected long duration = 0;
    protected String id = "error";
    protected final static int SIZE = 100000;
    protected static int[] preLoaded = new int[SIZE];

    static {
        Random random = new Random(47);
        for (int i = 0; i < SIZE; i++)
            preLoaded[i] = random.nextInt();
    }

    public abstract void accumulate();
    public abstract long read();

    private class Modifier implements Runnable {
        @Override
        public void run() {
            for (long i=0;i<cycles;i++)
                accumulate();
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    private class Reader implements Runnable {
        private volatile long value;
        @Override
        public void run() {
            for (long i=0;i<cycles;i++)
                value = read();
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public void timedTest() {
        long start = System.nanoTime();
        for (int i=0;i<N;i++) {
            executor.execute(new Modifier());
            executor.execute(new Reader());
        }
        try {
            barrier.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        duration = System.nanoTime() - start;

    }

    public static void report(Accumulator acc1, Accumulator acc2) {
        //acc1.duration/acc2.duration;
    }
}

class BaseLine extends Accumulator {
    { id = "BaseLine";}
    @Override
    public void accumulate() {
        value += preLoaded[index++];
        if (index >= SIZE) index = 0;
    }

    @Override
    public long read() {
        return value;
    }
}

class SynchnizeTest extends Accumulator {
    {id = "synchronized";}
    @Override
    public void accumulate() {
        value += preLoaded[index++];
        if (index >= SIZE) index = 0;
    }

    @Override
    public long read() {
        return value;
    }
}

class LockTest extends Accumulator {
    {id = "Lock";}
    private Lock lock = new ReentrantLock();
    @Override
    public void accumulate() {
        lock.lock();
        try {
            value += preLoaded[index++];
            if (index >= SIZE) index = 0;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public long read() {
        lock.lock();
        try {
            return value;
        } finally {
            lock.unlock();
        }
    }
}

class AtomicTest extends Accumulator {
    {id = "atomic";}
    private AtomicInteger atomicInteger = new AtomicInteger(0);
        private AtomicLong atomicLong = new AtomicLong(0);
    @Override
    public void accumulate() {
        int i = atomicInteger.incrementAndGet();
        atomicLong.getAndAdd(preLoaded[i]);
        if (++i >= SIZE) atomicInteger.set(0);
    }

    @Override
    public long read() {
        return atomicLong.get();
    }
}
public class SynchronizedCOmprisions {
    static BaseLine baseLine = new BaseLine();
    static SynchnizeTest synchnizeTest = new SynchnizeTest();
    static LockTest lockTest = new LockTest();
    static AtomicTest atomicTest = new AtomicTest();
    static void test() {
        System.out.println("=============");
        baseLine.timedTest();
        synchnizeTest.timedTest();
        lockTest.timedTest();
        atomicTest.timedTest();
        Accumulator.report(synchnizeTest, baseLine);
        Accumulator.report(lockTest, baseLine);
        Accumulator.report(atomicTest, baseLine);
        Accumulator.report(lockTest, baseLine);
    }

    public static void main(String[] args) {

    }
}