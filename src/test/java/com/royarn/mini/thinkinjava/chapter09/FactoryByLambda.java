package com.royarn.mini.thinkinjava.chapter09;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-21
 */
public class FactoryByLambda {

    public static void main(String[] args) {
        getInstance();
    }

    public static Ablity getInstance() {
        return () -> System.out.println("Factory by lambda");
    }
}

@FunctionalInterface
interface Ablity {
    void play();
}
