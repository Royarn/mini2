package com.royarn.mini.thinkinjava.chapter09;

import java.util.Arrays;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-20
 */
public class Music5 {

    static void tune(Animal animal) {
        animal.play(Type.JUMP);
    }

    static void tuneAll(Animal[] animals) {
        Arrays.asList(animals)
                .stream()
                .forEach(Music5::tune);
    }

    public static void main(String[] args) {
        Animal[] animals = {
                new Dog(),
                new Cat(),
                new Horse(),
                new TuDog(),
                new HaDog()
        };
        tuneAll(animals);
    }
}

enum Type {
    JUMP, RUN;
}

interface Animal {
    void play(Type type);
    String what();
    void adjust();
}

class Dog implements Animal {
    @Override
    public void play(Type type) {
        System.out.println("Dog.play() " + type);
    }

    @Override
    public String what() {
        return "Dog";
    }

    @Override
    public void adjust() {
    }
}

class Cat implements Animal {
    @Override
    public void play(Type type) {
        System.out.println("Cat.play() " + type);
    }

    @Override
    public String what() {
        return "Cat";
    }

    @Override
    public void adjust() {

    }
}

class Horse implements Animal {
    @Override
    public void play(Type type) {
        System.out.println("Horse.play() " + type);
    }

    @Override
    public String what() {
        return "Horse";
    }

    @Override
    public void adjust() {

    }
}

class TuDog extends Dog {
    @Override
    public void play(Type type) {
        System.out.println("TuDog.play() " + type);
    }

    @Override
    public String what() {
        return "TuDog";
    }
}

class HaDog extends Dog {
    @Override
    public void play(Type type) {
        System.out.println("HaDog.play() " + type);
    }

    @Override
    public String what() {
        return "HaDog";
    }
}