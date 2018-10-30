package com.royarn.mini.atomic;

import com.royarn.mini.annotation.ThreadSafe;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-29
 */

/**
 * volatile 使用原则：
 *  1.当前线程修改volatile变量不依赖当前值
 *  2.变量不需要与其他的变量共享约束
 *  3.访问变量时没有其他的原因加锁
 *
 *  保证数据安全一致性的几种手段：
 *      使用各种安全机制保证共享、可变数据：
 *          1.使用同步机制，若使用sync锁保证操作原子性，并且其更改对其他线程可见
 *          2.使用volatile限制共享变量，保证其修改安全并且对其他线程可见
 *          3.使用原子类型变量操作数据
 *      使用线程封闭机制保证数据不可见：
 *          1.Ad-hoc单线程化
 *          2.使用本地执行栈机制保证变量对其他线程不可见，防止本地数据逸出
 *          3.使用ThreadLocal机制保证数据独占
 *          4.使用final关键字保证数据不可变
 *
 *
 *  线程安全策略思想：
 *      将数据封装在对象内部，把对数据的访问限制在方法上，更易确保线程在访问数据时总能获得正确的锁
 */
@ThreadSafe
public class CountingFactory {

    private AtomicLong count = new AtomicLong(0);
    public static CountingFactory countingFactory = new CountingFactory();

    public void setCount() {
        count.incrementAndGet();
    }

    public long getCount() {return count.get();}


    public static void main(String[] args) {
        countingFactory.setCount();
//        final CountingFactory factory = new CountingFactory();
//        factory.setCount();
//        factory.setCount();
//        factory.setCount();
//        factory.setCount();
//        for (int i=0;i<20;i++) {
//            final int temp = i;
//           new Thread(
//                   () -> {
//                       factory.setCount();
//                   System.out.println("Thread " + temp + " has finished : " + factory.getCount());
//                   }
//           ).start();
//        }
        System.out.println(countingFactory.getCount());
    }

    public List<Integer> getCollection() {
        return Arrays.asList(1, 2, 3, 4);
    }
}