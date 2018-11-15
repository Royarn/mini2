package com.royarn.mini.thinkinjava.chapter06;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-15
 */
public class Car {

    Engine engine;
    Wheel[] wheels;
    Door left, right;

    public Car() {
        engine = new Engine();
        wheels = new Wheel[4];
        left = new Door();
        right = new Door();
        for (int i=0;i<4;i++) {
            wheels[i] = new Wheel();
        }
    }

    public static void main(String[] args) {
        Car car = new Car();
        car.left.window.rollup();
        car.wheels[0].inflate();
    }
}

class Engine {
    void start() {}
    void rev() {}
    void stop() {}
}

class Wheel {
    void inflate() {}
}

class Window {
    void rollup() {}
    void rolldown() {}
}

class Door {
    public Window window = new Window();
    void open() {}
    void close() {}
}