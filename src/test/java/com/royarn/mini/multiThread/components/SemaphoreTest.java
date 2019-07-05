package com.royarn.mini.multiThread.components;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author lizhiqiang
 * @date 2019-06-14
 */
public class SemaphoreTest {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(10);
        Job job = new Job(semaphore);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i=0;i<200; i++) {
            final int nThreadNum = i;
            job.setNthreadNum(nThreadNum);
            service.execute(() -> job.doJob());
        }
        Thread.sleep(100000);
    }
}

class Job {
    private Semaphore semaphore;
    private int nthreadNum;
    public Job(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public int getNthreadNum() {
        return nthreadNum;
    }

    public void setNthreadNum(int nthreadNum) {
        this.nthreadNum = nthreadNum;
    }

    public void doJob() {
        try {
            semaphore.acquire();
            Thread.sleep(2000);
            System.out.println("Thread-pool-" + nthreadNum + " : do something");
        } catch (Exception e) {

        } finally {
            semaphore.release();
        }
    }
}
