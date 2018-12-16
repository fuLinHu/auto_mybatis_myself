package com.framk.autocode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@MapperScan("com.framk.autocode.dao")
public class AutocodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutocodeApplication.class, args);
    }

}

