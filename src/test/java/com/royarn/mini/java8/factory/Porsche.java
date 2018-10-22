package com.royarn.mini.java8.factory;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-22
 */
public class Porsche extends Car {

    private String name;
    private String country;

    public Porsche() {
        this("porsche", "Italy");
    }

    public Porsche(String name, String country) {
        super(name);
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
