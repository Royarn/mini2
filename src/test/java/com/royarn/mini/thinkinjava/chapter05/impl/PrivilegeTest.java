package com.royarn.mini.thinkinjava.chapter05.impl;

import com.royarn.mini.thinkinjava.chapter05.Pojo;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-09
 */

/**
 * for protected privilege
 *  you can use in the same package, and what diff is you can use it by initial subclass
 */
public class PrivilegeTest extends Pojo{

    public static void main(String[] args) {
        new PrivilegeTest().testProtcted();
        System.out.println();
    }
}