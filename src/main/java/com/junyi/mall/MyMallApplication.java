package com.junyi.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.junyi.mall.dao")
public class MyMallApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyMallApplication.class, args);
	}

}
