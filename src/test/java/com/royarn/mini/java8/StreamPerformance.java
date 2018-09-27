package com.royarn.mini.java8;

import java.util.function.Function;
import java.util.stream.Stream;

public class StreamPerformance {

    public static void main(String[] args) {
        System.out.println("Sequential sum done in : " +
        measureSumPerf(StreamPerformance::parallelSum, 10_000_000) + "msecs");
    }

    /**
     * @param addr
     * @param n
     * @return
     */
    public static long measureSumPerf(Function<Long, Long> addr, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = addr.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result : " + sum);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }

    /**
     * @param n
     * @return
     */
    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    /**
     * @param n
     * @return
     */
    public static long iterativeSum(long n) {
        long result = 0;
        for (int i = 0; i <= n; i++) {
            result += i;
        }
        return result;
    }

    /**
     * @param n
     * @return long
     */
    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }
}
