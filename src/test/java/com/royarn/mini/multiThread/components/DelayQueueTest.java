package com.royarn.mini.multiThread.components;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-21
 */
public class DelayQueueTest {
}
class DelayTask implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final int delta =0;
    private final long trigger;
    protected static List<DelayTask> tasks = new ArrayList<>();
    public DelayTask(int delay) {
        delay = delay;
        trigger = System.nanoTime();
    }
    @Override
    public void run() {

    }
}
