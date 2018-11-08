package com.royarn.mini.thinkinjava.chapter03;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-08
 */
public class OrderInitialization {

    public static void main(String[] args) {
        Card card = new Card();
        //shows construction is done
        card.f();
    }
}

//mock
class Tag {
    Tag(int marker) {
        System.out.println("Marker (" + marker + ")");
    }
}

class Card {

    //before constructor
    Tag t1 = new Tag(1);

    Card() {
        System.out.println("Card()");
        t3 = new Tag(33);
    }

    //after constructor
    Tag t2 = new Tag(2);

    void f() {
        System.out.println("f()");
    }

    //at end
    Tag t3 = new Tag(3);
}