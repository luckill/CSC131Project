package com.example.CSC131Project;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;




@SpringBootApplication
@EnableConfigurationProperties(ApiConfiguration.class)
public class Csc131ProjectApplication
{
	public static void main(String[] args) throws JsonProcessingException {
		SpringApplication.run(Csc131ProjectApplication.class, args);
	}

}
