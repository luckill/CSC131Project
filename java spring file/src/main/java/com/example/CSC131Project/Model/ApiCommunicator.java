package com.example.CSC131Project.Model;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

//Communicates with the IMDB api to get info on movies
public class ApiCommunicator
{
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

    public static String getRequest(String movie)
    {
        movie = movie.replaceAll(" ", "_").toLowerCase();
        String API_KEY = "apikey=d860e921";
        //String movie = "?t=iron_man";
        String url = "http://www.omdbapi.com/?t=" + movie + "&" + API_KEY;

        HttpHeaders headers= new HttpHeaders();
        headers.add("Authorization", "d860e921");
        HttpEntity request = new HttpEntity(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        String result = response.getBody();
        return result;
    }
}
