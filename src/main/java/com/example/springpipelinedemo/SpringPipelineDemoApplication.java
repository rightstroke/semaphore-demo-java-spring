package com.example.springpipelinedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.springpipelinedemo.service.UserService

@SpringBootApplication
public class SpringPipelineDemoApplication {
	//@Autowired
	//private UserService userService;

    public static void main(String[] args) {
	String ABCD = "Virtusa Test Line";
        SpringApplication.run(SpringPipelineDemoApplication.class, args);
        //userService.createUser("test@email.com", "testPass123");
    }
    
    @Bean
    CommandLineRunner init (UserService userService){
        return args -> {
            //List<String> names = Arrays.asList("udara", "sampath");
            //names.forEach(name -> studentRepo.save(new Student(name)));
        	//userService.createUser("admin@admin.com", "admin123");
        };
    }

}

