package com.example.CSC131Project.Controller;

import com.example.CSC131Project.ApiCommunicator;
import com.example.CSC131Project.Model.Movie;
import com.example.CSC131Project.Model.MovieRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController
{
    @Autowired
    public MovieRepository movieRepository;
    @GetMapping("/getMovieById/{movieId}")
    public String getMovieById(@PathVariable String movieId)
    {
        //placeholder, replace with your code
        return "";
    }
    @GetMapping("/getMovieByTitle/{title}")
    public String getMovieByTitle(@PathVariable String title)
    {
        return "";
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