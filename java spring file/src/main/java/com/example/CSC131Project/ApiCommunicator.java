package com.example.CSC131Project;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;


public class ApiCommunicator {
    public String getRequest(String url, String KEY)
    {
        String API_KEY = "apikey=d860e921";
        String movie = "?t=iron_man";
        url = "http://www.omdbapi.com/" + movie + "&" + API_KEY;

        HttpHeaders headers= new HttpHeaders();
        headers.add("Authorization", "d860e921");
        HttpEntity request = new HttpEntity(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        String result = response.getBody();
        return result;
    }

}
