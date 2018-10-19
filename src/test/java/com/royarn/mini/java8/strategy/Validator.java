package com.royarn.mini.java8.strategy;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-19
 */
public class Validator {

    private final ValidateStrategy strategy;

    public Validator(ValidateStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean validate(String s) {
        return strategy.excute(s);
    }

    public static void main(String[] args) {
        //使用lambda表达式简化函数式接口 --替代匿名内部类
        boolean isNumber = new Validator(
                str -> str.matches("\\d+"))
                .validate("qwe");

        boolean isLowCase = new Validator(
                str -> str.matches("[a-z]+"))
                .validate("Blink");
        System.out.println(isLowCase);
    }
}
