package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

// class with this annotation, must have the main method in it
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		// getting the beanbag
		ApplicationContext ac = SpringApplication.run(DemoApplication.class, args);
	
		Object beanByName = ac.getBean("getDate");
		String beanByType = ac.getBean(String.class);
		String beanByNameAndType = ac.getBean("getDate", String.class);
		
		System.out.println(beanByName);
		System.out.println(beanByType);
		System.out.println(beanByNameAndType);
	}

	@Bean
	public String getDate() {
		return "30-10-13";
	}
}
