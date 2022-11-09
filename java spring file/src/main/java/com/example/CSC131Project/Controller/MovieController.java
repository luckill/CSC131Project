package com.example.CSC131Project.Controller;

import com.example.CSC131Project.ApiCommunicator;
import com.example.CSC131Project.Model.Movie;
import com.example.CSC131Project.Model.MovieRepository;
import com.example.CSC131Project.interfaceService.ImovieService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;








//@ResponseBody

// @RestController
@Controller
@RequestMapping
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