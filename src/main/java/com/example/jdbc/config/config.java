package com.example.jdbc.config;

import com.example.jdbc.model.Student;
import com.example.jdbc.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {

//    @Bean
    public CommandLineRunner createUser(StudentRepository studentRepository){
        System.out.println("Running Create Student");
       return args ->  studentRepository.createuser();
    }
//    @Bean
    public CommandLineRunner getUser(StudentRepository studentRepository){
        System.out.println("Running Create Student");
        return args ->  studentRepository.getUser();
    }

    @Bean
    public CommandLineRunner createUserWithPreparedStatement(StudentRepository studentRepository){
        System.out.println("Running Create Student");
        Student student = new Student("Sibasis","siba@gmail.com",25);
        return args ->  studentRepository.createUserWithPreparedStatement(student);
    }


}

