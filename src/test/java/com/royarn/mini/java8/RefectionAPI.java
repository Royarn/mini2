package com.royarn.mini.java8;

import java.lang.reflect.InvocationTargetException;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-05
 */
public class RefectionAPI {

    public static void main(String[] args) {
        try {
            Obj obj = Obj.class.getDeclaredConstructor().newInstance();
            obj.setName("tst");
            System.out.println(obj);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}

class Obj {

    private int id;
    private String name;
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "{ id: " + id + ", name: " + name + ", remark: " + remark + " }";
    }
}