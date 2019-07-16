package com.royarn.mini;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableDubbo
@EnableTransactionManagement(order = 10)
@SpringBootApplication
@MapperScan(basePackages = {"com.royarn.mini.dao"})
@EnableScheduling
public class MiniApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniApplication.class, args);
	}
}