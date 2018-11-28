package com.royarn.mini.thinkinjava.chapter10;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-28
 */
public class CarFactory {

    public static Car BMWCar() {
        return () -> System.out.println("BMW id running ...");
    }

    public static Car BSJCar() {
        return () -> System.out.println("BSJ id running ...");
    }

    public static void main(String[] args) {
        Car car = CarFactory.BMWCar();
        car.run();
    }
}

interface Car {
    void run();
}