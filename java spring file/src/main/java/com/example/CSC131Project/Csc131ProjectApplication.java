package com.example.CSC131Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class Csc131ProjectApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(Csc131ProjectApplication.class, args);
	}
	@GetMapping("/hello")
	public List<String> hello()
	{
		return List.of("Hello", "World");
	}

}