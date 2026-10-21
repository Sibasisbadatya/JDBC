package com.example.jdbc;

import com.example.jdbc.repository.StudentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JdbcApplication {

    public static void main(String[] args) {

        SpringApplication.run(JdbcApplication.class, args);

    }

}
