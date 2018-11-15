package com.royarn.mini.thinkinjava.chapter08;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-15
 */
public class Music {

    public static void tune(Instrument i) {
        i.play(Note.MIDDLE_C);
        i.adjust();
        i.what();
    }

    public static void tuneAll(Instrument[] array) {
        for (int i=0;i<array.length;i++) {
            tune(array[i]);
        }
    }

    public static void main(String[] args) {
       Instrument[] instruments = {
               new Wind(),
               new Percussion(),
               new Stringed(),
               new Woodwind(),
               new Brass()
       };
       tuneAll(instruments);
    }
}

enum Note {
    MIDDLE_C, C_SHARP, B_FLAT;
}

class Instrument {
    void play(Note note) {
        System.out.println("Instrument.play()");
    }

    String what() {
        System.out.println("Instrument.what()");
        return "";
    }

    void adjust() {
        System.out.println("Instrument.adjust()");
    }
}

class Wind extends Instrument {
    void play(Note note) {
        System.out.println("Wind.play()" + note);
    }
    String what() {
        System.out.println("Wind.what()");
        return "";
    }

    void adjust() {
        System.out.println("Wind.adjust()");
    }
}

class Percussion extends Instrument {
    void play(Note note) {
        System.out.println("Percussion.play()" + note);
    }
    String what() {
        System.out.println("Percussion.what()");
        return "";
    }

    void adjust() {
        System.out.println("Percussion.adjust()");
    }
}

class Stringed extends Instrument {
    void play(Note note) {
        System.out.println("Stringed.play()" + note);
    }
    String what() {
        System.out.println("Stringed.what()");
        return "";
    }

    void adjust() {
        System.out.println("Stringed.adjust()");
    }
}

class Woodwind extends Wind {
    void play(Note note) {
        System.out.println("Woodwind.play()" + note);
    }
    String what() {
        System.out.println("Woodwind.what()");
        return "";
    }
}

class Brass extends Wind {
    void play(Note note) {
        System.out.println("Brass.play()" + note);
    }
    void adjust() {
        System.out.println("Brass.adjust()");
    }
}