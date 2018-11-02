package com.royarn.mini;


import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDubbo
@EnableTransactionManagement(order = 10)
@SpringBootApplication
@MapperScan(basePackages = {"com.royarn.mini.dao"})
public class MiniApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniApplication.class, args);
	}
}