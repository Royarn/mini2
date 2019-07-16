package com.royarn.mini.spring.mybatis;


import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;

/**
 * Description:
 *
 * @author lizhiqiang
 * @date 2019-07-15
 */
@Configuration
@ComponentScan("com.royarn.mini.spring.mybatis")
public class SqlSeesionTest {

    public static void main(String[] args) {
    }

}

