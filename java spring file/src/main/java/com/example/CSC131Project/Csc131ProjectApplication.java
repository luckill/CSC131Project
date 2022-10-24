package com.example.CSC131Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
@RestController
public class Csc131ProjectApplication
{
	public static void main(String[] args)
	{

		SpringApplication.run(Csc131ProjectApplication.class, args);
	}

}
