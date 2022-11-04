package com.example.CSC131Project.Model;
import com.example.CSC131Project.ApiConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

//Communicates with the IMDB api to get info on movies
public class ApiCommunicator
{
    public String getRequest(String movie, String key)
    {
        String url = "http://www.omdbapi.com/?t=" + movie + "&" + "apikey=" + key;

        HttpHeaders headers= new HttpHeaders();
        headers.add("Authorization", key);
        HttpEntity request = new HttpEntity(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        return response.getBody();
    }
}
