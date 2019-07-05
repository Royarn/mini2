package com.royarn.mini.multiThread.components;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 *
 * @author lizhiqiang
 * @date 2019-06-14
 */
public class CustomCache {

    private final Map<String, Integer> cache = new HashMap<>();
    private Transfer<String, Integer> transfer;
    public CustomCache() {
        transfer = (key) -> {
            key = key.substring(0, 2);
            if (!cache.containsKey(key)) {
                cache.put(key, key.hashCode());
            }
            return 1;
        };
    }

    public Map<String, Integer> getCache() {
        return this.cache;
    }

    public synchronized void cache(String key) {
        transfer.comoute(key);
    }

    public static void main(String[] args) throws InterruptedException {
        //高并发 大量线程写入
        CustomCache cache = new CustomCache();
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i=0;i<200;i++) {
            service.submit(() -> cache.cache(UUID.randomUUID().toString()));
            }
        //service.shutdown();
        while (true) {
            System.out.println("获取结果：" + cache.getCache() + " 总数：" + cache.getCache().keySet().size());
            Thread.sleep(1500);
        }
    }
}

@FunctionalInterface
interface Transfer<S, I> {
    I comoute(S data);
}