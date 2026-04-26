package com.mpj.miniproj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.mpj.miniproj")
public class MiniprojApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniprojApplication.class, args);
	}

}
