package com.zetta.memo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@MapperScan(basePackages = {"com.zetta.memo.page.*"})
@SpringBootApplication
public class MemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(MemoApplication.class, args);
	}

}
