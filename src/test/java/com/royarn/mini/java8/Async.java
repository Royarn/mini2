package com.royarn.mini.java8;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-23
 */

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 构建异步执行API
 */
public class Async {
}

class Shop {

    private String product;

    private static final List<Shop> shops = Arrays.asList(new Shop("taobao"),
            new Shop("JD"),
            new Shop("weipinhui"),
            new Shop("GuoMei"),
            new Shop("GuoMei"),
            new Shop("GuoMei"),
            new Shop("GuoMei"),
            new Shop("GuoMei"),
            new Shop("GuoMei"),
            new Shop("GuoMei"),
            new Shop("GuoMei"),
            new Shop("GuoMei"),
            new Shop("GuoMei"),
            new Shop("GuoMei"),
            new Shop("GuoMei"),
            new Shop("GuoMei"),
            new Shop("GuoMei"),
            new Shop("GuoMei"),new Shop("GuoMei"),
            new Shop("GuoMei"),
            new Shop("GuoMei"),new Shop("GuoMei"),
            new Shop("GuoMei"),new Shop("GuoMei"));

    /**
     * thread pool framework
     */
    private static final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    return t;
                }
            }
            );

    /**
     * random delay instead of fixed delay
     */
    private static final Random random = new Random();

    public Shop(String product) {
        this.product = product;
    }

    public String getProduct() {
        return product;
    }

    public static String getPrice(String product, Executor executor) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[new Random().nextInt(Discount.Code.values().length)];
        return String.format("%s: %.2f : %s", product, price, code);
    }

    /**
     * remote service
     * best price query
     * Sync implement
     * @param product
     * @return
     */
    public static double getPriceSync(String product) {
        return calculatePrice(product);
    }

    private static double calculatePrice(String product) {
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    /**
     * mock third party API call
     */
    public static void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * mock third party API call
     */
    public static void randomDelay() {
        try {
            Thread.sleep(500 + random.nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * best price query
     * Async implement
     * @param product
     * @return
     */
    public static Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> completableFuture = new CompletableFuture<>();
        new Thread(() -> {
            double price  = calculatePrice(product);
            completableFuture.complete(price);
        }).start();
        return completableFuture;
    }

    //use official API
    public static Future<Double> getPirceByAPI(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    public static void main(String[] args) {
        //when you use async compute , they will return right now
        //when you get the result, it will be sync
        //Future<Double> doubleFuture = new Shop().getPriceAsync("tomato");
        /**Future<Double> doubleFuture = new Shop().getPirceByAPI("tomato");
        //then you can call other methods or compute without block
        doSomethingelse();
        //this will be sync when you get the result
        try {
            double result = doubleFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/

        //compare sync and aync
        /**long start = System.nanoTime();
        //use sync and parallel
        System.out.println(findPrices("GUOmei"));
        //use async
        System.out.println(findPricesByFullAsync("taobao"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " secs");*/


        //combine diff async
        /**long start = System.nanoTime();
        //use sync or parallel sync
        //findRealPriceSync("taobao");
        //use async and executor
        findRealPriceByAsync("taobao");
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " secs");*/

        //use async and real-time
        long start = System.nanoTime();
        CompletableFuture[] completableFutures = findRealPriceWithRealTime("taobao")
                .map(future -> future.thenAccept(
                        s -> System.out.println(s + " ( Done in " + (System.nanoTime() - start)/1_000_000 + " secs )")
                ))
                .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(completableFutures).join();
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " secs");
    }

    private static void doSomethingelse() {
    }

    public static List<String> findPrices(String product) {
        //use sync  query by parallel
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f",
                            shop.getProduct(), getPriceSync(product)))
                .collect(Collectors.toList());
        //use full async
        /**return shops.stream()
                .map(shop ->
                CompletableFuture.supplyAsync(() ->
                        String.format("%s price is %.2f",
                                shop.getProduct(), getPriceSync(product)
                )))
                .collect(Collectors.toList());*/
    }

    public static List<String> findPricesByFullAsync(String product) {
        //use full sync
        //创建异步执行流程
        List<CompletableFuture<String>> completableFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> String.format("%s price is %.2f",
                                shop.getProduct(), getPriceSync(product))
                ))
                .collect(Collectors.toList());
        return completableFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public static List<String> findRealPriceSync(String product) {
        //use parallel and sync
        return shops.parallelStream()
                .map(shop -> getPrice(shop.getProduct(), executor))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    public static List<String> findRealPriceByAsync(String product) {
        List<CompletableFuture<String>> completableFutures = shops.stream()
        .map(shop -> CompletableFuture.supplyAsync(
                () -> getPrice(shop.getProduct(), executor)
        ))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote ->
                CompletableFuture.supplyAsync(
                        () -> Discount.applyDiscount(quote), executor
                )
                ))
                .collect(Collectors.toList());
        return completableFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public static Stream<CompletableFuture<String>> findRealPriceWithRealTime(String product) {
        return shops.stream()
        .map(shop -> CompletableFuture.supplyAsync(
                () -> getPrice(shop.getProduct(), executor)
        ))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote ->
                        CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)
                ));
    }
}