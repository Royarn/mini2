package com.royarn.mini.collection;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/7/12 9:52
 */
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.royarn.mini.util.GsonUtils;

import java.util.*;
import java.util.concurrent.CountDownLatch;

import static com.royarn.mini.collection.ResType.*;
public class Test {

    public static void main(String[] args) {
        ResType resType = valueOf("platform");
        MockStack<String> stack = new MockStack<>();
        stack.offer("blink");
        stack.offer("filnk");
        stack.offer("klink");
        Queue<String> queue = new LinkedList<>();
        queue.offer("blik");
        queue.poll();
        MQ mq = new MQ();
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println(mq.producer(UUID.randomUUID().toString()));
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            try {
                System.out.println(mq.consumer());
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    static class MockStack<T> {
        private LinkedList<T> list = new LinkedList<>();

        public T offer(T t) {
            list.addFirst(t);
            return t;
        }

        public T peek() {
            return list.getFirst();
        }

        public T pop() {
            return list.removeFirst();
        }

        public int size() {
            return list.size();
        }
    }

    static class MQ {
        private Queue<String> queue = new LinkedList<>();

        public String producer(String msg) {
            queue.offer(msg);
            return "=====入队成功========" + msg;
        }

        public String consumer() {
            return "=======出队成功======" + queue.poll();
        }
    }
}