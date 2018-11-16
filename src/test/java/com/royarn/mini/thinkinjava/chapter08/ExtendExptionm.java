package com.royarn.mini.thinkinjava.chapter08;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-16
 */

/**
 * this will be input
 * Glyph() before print()
 * RoundGlyph.RoundGlyph(), raduis = 0
 * Glyph() before print()
 * System.out.println("RoundGlyph.RoundGlyph(), raduis = 5
 *
 */
public class ExtendExptionm {

    public static void main(String[] args) {
        System.out.println(new RoundGlyph(5));
    }
}

class Glyph {

    public Glyph() {
        System.out.println("Glyph() before print()");
        print();
        System.out.println("Glyph() before print()");
    }

    void print() {
        System.out.println("Glyph() print()");
    }
}

class RoundGlyph extends Glyph {

    private int raduis = 1;

    public RoundGlyph(int raduis) {
        System.out.println(this.raduis);
        this.raduis = raduis;
        System.out.println("RoundGlyph.RoundGlyph(), raduis = " + raduis);
    }

    void print() {
        System.out.println("RoundGlyph.RoundGlyph(), raduis = " + raduis);
    }
}