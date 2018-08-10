package com.royarn.mini.reflection;

import java.util.Date;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/3 9:43
 */
public class PojoClass extends SuperPojo{

    private String var1;
    String var2;
    protected Date date;
    public Integer number;

    public PojoClass() {
        super("superAttribute");
    }

    private PojoClass(String var1,String var2) {
        this.var1 = var1;
        this.var2 = var2;
    }

    public PojoClass(String var1, String var2, Date date, Integer number) {
        super(var1);
        this.var1 = var1;
        this.var2 = var2;
        this.date = date;
        this.number = number;
    }

    public String getVar1() {
        return var1;
    }

    public void setVar1(String var1) {
        this.var1 = var1;
    }

    public String getVar2() {
        return var2;
    }

    public void setVar2(String var2) {
        this.var2 = var2;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    private void simpleMethod() {
        System.out.println("this is a private method ");
    }
}
