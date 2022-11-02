package com.example.CSC131Project.Controller;

import com.example.CSC131Project.Model.ApiCommunicator;
import com.example.CSC131Project.Model.Movie;
import com.example.CSC131Project.Model.MovieRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@Component
@Service
@RequestMapping("/movies")
public class MovieController
{
    @Autowired
    MovieRepository movieRepository;
    ApiCommunicator IMDBApi = new ApiCommunicator();

    @GetMapping("/getMovieById/{movieId}")
    public String getMovieById(@PathVariable String movieId)
    {
        //placeholder, replace with your code
        return "";
    }
    @GetMapping("/getMovieByTitle/{title}")
    public String getMovieByTitle(@PathVariable String title) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        if(movieRepository.findByTitle(title) != null){
            return objectMapper.writeValueAsString(movieRepository.findByTitle(title).get(0));
        }
        String json = IMDBApi.getRequest(title);
        Movie thisMovie = objectMapper.readValue(json, Movie.class);
        thisMovie = movieRepository.save(thisMovie);
        return objectMapper.writeValueAsString(thisMovie);
    }
    @PostMapping("/addMovie/{title}")
    public String addMovie(@PathVariable String title) throws ParseException
    {
        return "";
    }
    @PutMapping("")
    public String updateMovie()
    {
        return "";
    }
    @DeleteMapping("/delete/{movieId}")
    public String deleteMovieById(@PathVariable String movieId)
    {
        return"";
    }

}