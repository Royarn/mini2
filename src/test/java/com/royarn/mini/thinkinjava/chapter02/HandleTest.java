package com.royarn.mini.thinkinjava.chapter02;


/**
 * Description:
 *
 * @author dell
 * @date 2018-11-07
 */
public class HandleTest {

    public static void main(String[] args) {
        //get system property
        System.getProperties().list(System.out);
        //get memory
        Runtime runtime = Runtime.getRuntime();
        System.out.println("---Memory Usage");
        System.out.println("Total Memory = " + (runtime.totalMemory() / 1024l));
        System.out.println("Free Memory = " + (runtime.freeMemory() / 1024l));
    }
}