package com.example.demo;

import com.example.demo.configuration.formatterLocalDate.FormatterLocalDate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Configuration
    static class MyConfig extends WebMvcConfigurerAdapter {
        @Override
        public void addFormatters(FormatterRegistry registry) {
//            FormatterLocalDate formatterLocalDate = new FormatterLocalDate("yyyy-MM-dd");
            registry.addFormatter(new FormatterLocalDate("yyyy-MM-dd"));
        }
    }
}