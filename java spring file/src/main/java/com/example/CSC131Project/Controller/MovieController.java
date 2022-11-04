package com.example.CSC131Project.Controller;

import com.example.CSC131Project.Model.ApiCommunicator;
import com.example.CSC131Project.Model.Movie;
import com.example.CSC131Project.Model.MovieRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@Component
@Service
@RequestMapping("/movies")
public class MovieController
{
    @Autowired
    MovieRepository movieRepository;
    ApiCommunicator IMDBApi = new ApiCommunicator();
    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    @GetMapping("/getMovieById/{movieId}")
    public String getMovieById(@PathVariable String movieId)
    {
        //placeholder, replace with your code
        return "";
    }
    //Finds movie as a title first looks through the db
    //if nothing found in db goes to the IMDBApi and adds it to the db and returns it
    @GetMapping("/getMovieByTitle/{title}")
    public String getMovieByTitle(@PathVariable String title) throws JsonProcessingException {
        List<Movie> movies = movieRepository.findByTitle(title);
        if(movies.size() != 0){
            return objectMapper.writeValueAsString(movies.get(0));
        }
        String json = IMDBApi.getRequest(title);
        if(!json.contains("Movie not found!"))
        {
            Movie thisMovie = objectMapper.readValue(json, Movie.class);
            thisMovie = movieRepository.save(thisMovie);
            return objectMapper.writeValueAsString(thisMovie);
        }
        return objectMapper.writeValueAsString(objectMapper.readTree(json));
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
    @Modifying
    @DeleteMapping("/delete/{ID}")
    public String deleteMovieById(@PathVariable int ID) throws JsonProcessingException {

    }

}