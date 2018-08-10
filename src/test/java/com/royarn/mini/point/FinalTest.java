package com.royarn.mini.point;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/8 17:09
 */
public class FinalTest {

    private final Map<String, String> map = new HashMap<>();

    public static void main(String[] args) {
        FinalTest test = new FinalTest();
        test.addData("royarn", "763094810@qq.com");
        test.addData("blink", "763094810@qq.com");
        System.out.println(test.getData());
        System.out.println(FinalTest.class.getClassLoader());
        System.out.println(String.class.getClassLoader());
    }

    public void addData(String key, String value) {
        map.put(key, value);
    }

    public Map<String, String> getData() {
        return map;
    }
}