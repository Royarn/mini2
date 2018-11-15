package com.royarn.mini.thinkinjava.chapter08;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-15
 */
public class SharpFactory {

    public static Sharp factory(int random) {
        switch (random) {
            case 1:
                return new Circle();
            case 2:
                return new Triangle();
            case 3:
                return new Square();
        }
        return null;
    }

    public static void main(String[] args) {
        for (int i=1; i<4;i++) {
            Sharp sharp = SharpFactory.factory(i);
            sharp.draw();
            sharp.erase();
        }
    }
}

class Sharp {
    void draw() {}
    void erase() {}
}

class Circle extends Sharp {
    @Override
    void erase() {
        System.out.println("Circle.erase()");
    }

    @Override
    void draw() {
        System.out.println("Circle.draw()");
    }
}

class Square extends Sharp {
    @Override
    void erase() {
        System.out.println("Square.erase()");
    }

    @Override
    void draw() {
        System.out.println("Square.draw()");
    }
}

class Triangle extends Sharp {
    @Override
    void erase() {
        System.out.println("Triangle.erase()");
    }

    @Override
    void draw() {
        System.out.println("Triangle.draw()");
    }
}