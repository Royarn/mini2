package com.royarn.mini.java8.singleton;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-20
 */
public class RealSingleton {

    /**
     * hungry pattern   pre_init
     * this is a unsafe_thread instance
     */

    private static RealSingleton realSingleton = new RealSingleton();
    private RealSingleton() {}
    public static RealSingleton getInstanceByPre() {
        return realSingleton;
    }

    /**
     * unsafe_thread instance   lazy_init
     * this is a unsafe_thread instance
     */
    public static RealSingleton getInstanceByLazy() {
        if (null == realSingleton) {
            realSingleton = new RealSingleton();
        }
        return realSingleton;
    }

    /**
     * safe_thread instance
     * it always has a lock, it will be have a bad performance
     */
    public synchronized static RealSingleton getInstanceBySafe() {
        if (null == realSingleton) {
            realSingleton = new RealSingleton();
        }
        return realSingleton;
    }

    /**
     * safe_thread
     * when a thread build an instance, another thread has get
     * ,it will result to get an not_health instance
     */
    public static RealSingleton getInstanceBySafe2() {
        if (null == realSingleton) {
            synchronized (RealSingleton.class) {
                if (null == realSingleton) {
                    realSingleton = new RealSingleton();
                }
            }
        }
        return realSingleton;
    }

    /**
     * safe_thread
     */

    private static class HelpClass {
        private static final RealSingleton realSingleton = new RealSingleton();
    }
    public static RealSingleton getInstance() {
        return HelpClass.realSingleton;
    }
}
