package com.star.wlh;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(UserApplication.class);
        //   ClassLoader classLoader = springApplication.getClassLoader();
        springApplication.run(args);
    }
}
