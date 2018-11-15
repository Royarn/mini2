package com.royarn.mini.thinkinjava.chapter06;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-15
 */
public class Orc extends Villian {

    private int orcNumber;
    public Orc(String name, int orcNumber) {
        super(name);
        this.orcNumber = orcNumber;
    }

    @Override
    public String toString() {
        return "{ orcNumber: " + orcNumber + super.toString();
    }

    public void changes(String name, int orcNumber) {
        setName(name);
        this.orcNumber = orcNumber;
    }

    public static void main(String[] args) {
        Orc orc = new Orc("Limburger", 12);
        System.out.println(orc);
        orc.changes("Bob", 19);
        System.out.println(orc);
    }
}

class Villian {

    private String name;

    public Villian(String name) {
        this.name = name;
    }

    protected final void setName(String name) {this.name = name;}

    @Override
    public String toString() {
        return "{ name: " + name + "}";
    }
}