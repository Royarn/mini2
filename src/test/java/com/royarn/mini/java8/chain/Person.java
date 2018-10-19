package com.royarn.mini.java8.chain;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-19
 */

/**
 * 使用责任链模式构建一个普通的POJO
 */
public class Person {

    private String name;
    private Boolean sex;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "person : {name: " + name + ", sex: " + (sex.booleanValue()? "男": "女") + ", age: " + age.intValue() + "}";
    }
}
