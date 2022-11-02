package com.example.CSC131Project;

import com.example.CSC131Project.Controller.AcademyAwardController;
import com.example.CSC131Project.Controller.MovieController;
import com.example.CSC131Project.Model.Movie;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
@RestController
public class Csc131ProjectApplication
{
	public static void main(String[] args) throws JsonProcessingException {
		SpringApplication.run(Csc131ProjectApplication.class, args);
	}

}
