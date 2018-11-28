package com.royarn.mini.thinkinjava.chapter09;

import com.royarn.mini.thinkinjava.chapter10.Parcel9;

import java.util.Arrays;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-19
 */
public class Music4 {

    static void tuneAll(Instrument[] instruments) {
        Arrays.asList(instruments)
                .stream()
                .forEach(Music4::tune);
    }

    static void tune(Instrument instrument) {
        instrument.play(Note.MIDDLE_C);
    }

    public static void main(String[] args) {
        Instrument[] instruments = {
          new Wind(),
          new Percussion(),
                new Stringed(),
                new Brass(),
                new WoodWind()
        };
        tuneAll(instruments);
    }
}

enum Note {
    MIDDLE_C, C_SHARP, B_FLAT;
}

abstract class Instrument {
    private int i;
    abstract void play(Note note);
    abstract void adjust();
    String what() {
        return "Instrument";
    }
}

class Wind extends Instrument {
    @Override
    void play(Note note) {
        System.out.println("Wind.play() " + note);
    }

    @Override
    void adjust() {
        System.out.println("Wind.play()");
    }

    @Override
    String what() {
        return "Wind";
    }
}

class Percussion extends Instrument {
    @Override
    void play(Note note) {
        System.out.println("Percussion.play() " + note);
    }

    @Override
    void adjust() {

    }

    @Override
    String what() {
        return "Percussion";
    }
}

class Stringed extends Instrument {
    @Override
    void play(Note note) {
        System.out.println("Stringed.play() " + note);
    }

    @Override
    void adjust() {

    }

    @Override
    String what() {
        return "Stringed";
    }
}

class Brass extends Wind {
    @Override
    void play(Note note) {
        System.out.println("Brass.play() " + note);
    }

    @Override
    void adjust() {
        System.out.println("Brass.adjust()");
    }
}

class WoodWind extends Wind {
    @Override
    void play(Note note) {
        System.out.println("WoodWind.play() " + note);
    }

    @Override
    String what() {
        return "WoodWind";
    }
}
