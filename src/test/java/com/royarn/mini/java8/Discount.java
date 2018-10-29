package com.royarn.mini.java8;

import com.alibaba.fastjson.JSON;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-23
 */
public class Discount {

    public enum Code {
        NONE(5), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        String result =  quote.getProduct() + " price is " +
                Discount.apply(quote.getPrice(), quote.getCode());
        return result;
    }

    private static String apply(double price, Code code) {
        delay();
        return String.valueOf(price * (100 - code.percentage)/100);
    }

    /**
     * mock third party API call
     */
    public static void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Quote {

    private String product;
    private double price;
    private Discount.Code code;

    public Quote(String product, double price, Discount.Code code) {
        this.product = product;
        this.price = price;
        this.code = code;
    }

    public String getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }

    public Discount.Code getCode() {
        return code;
    }

    /**
     *
     * parse shop
     * @param s
     * @return
     */
    public static Quote parse(String s) {
        String[] strings = s.split(":");
        return new Quote(strings[0].trim(), Double.parseDouble(strings[1].trim()), Discount.Code.valueOf(strings[2].trim()));
    }
}