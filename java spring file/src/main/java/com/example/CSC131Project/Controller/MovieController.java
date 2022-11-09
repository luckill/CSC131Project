package com.example.CSC131Project.Controller;

import com.example.CSC131Project.Model.ApiCommunicator;
import com.example.CSC131Project.Model.Movie;
import com.example.CSC131Project.Model.MovieRepository;
import com.example.CSC131Project.interfaceService.ImovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@Component
//@Service
@RequestMapping("/movies")

public class MovieController
{
    @Autowired
    public ImovieService service;
    @GetMapping("/list")
    public String list(Model model){
        List<Movie> movies =service.list();
        model.addAttribute("movies",movies);
        return "index";
    }

    @GetMapping("/new")
    public String add(Model model){
        model.addAttribute("movie",new Movie());
        return "AddForm";
    }
    @PostMapping("/save")
    public String save(@Valid Movie m, Model model){
        service.save(m);
        return "redirect:/list";
    }


    @Autowired
    public MovieRepository movieRepository;
    ApiCommunicator iMDBApi = new ApiCommunicator();
    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    @GetMapping("/getMovieById/{movieId}")
    public String getMovieById(@PathVariable String movieId)
    {
        //placeholder, replace with your code
        return "";
    }
    @GetMapping("/getMovieByTitle/{title}")

    public String getMovieByTitle(@PathVariable String title) throws JsonProcessingException {
        List<Movie> movies = movieRepository.findByTitle(title);
        if (movies.size() != 0) {
            return objectMapper.writeValueAsString(movies.get(0));
        }
        String json = iMDBApi.getRequest(title, apiConfiguration.AuthorizeToke());
        if (!json.contains("Movie not found!")) {
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
    @DeleteMapping("/delete/{movieId}")
    public String deleteMovieById(@PathVariable String movieId)
    {
        return"";
    }

}