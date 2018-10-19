package com.royarn.mini.java8.strategy;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-19
 */
public class IsNumeric implements ValidateStrategy {
    @Override
    public boolean excute(String s) {
        return s.matches("\\d+");
    }
}
