package com.example.day1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Day1Application {

	public static void main(String[] args) {
//		SpringApplication.run(Day1Application.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(Day1Application.class, args);
//		String[] names = context.getBeanDefinitionNames();
//		for (String name : names) {
//			System.out.println("name: " + name);
//		}
		System.out.println(context.getBeanDefinitionCount());

		// Use HelloSpring
//		HelloSpring b1 = context.getBean(HelloSpring.class);
//		b1.count = 100;
//		System.out.println(b1.count);
//		HelloSpring b2 = context.getBean(HelloSpring.class);
//		System.out.println(b2.count);
	}

}
