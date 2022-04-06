package com.example.fjtravel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration// @ComponentScan
public class FjTravelApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(FjTravelApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FjTravelApplication.class);
    }
}
