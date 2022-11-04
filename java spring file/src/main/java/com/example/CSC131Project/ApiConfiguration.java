package com.example.CSC131Project;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("api")
public record ApiConfiguration(String AuthorizeToke)
{

}
