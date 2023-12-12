package com.star.wlh;


import com.star.wlh.user.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
public class UserApplication {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(UserApplication.class);
        springApplication.run(args);
    }
}
