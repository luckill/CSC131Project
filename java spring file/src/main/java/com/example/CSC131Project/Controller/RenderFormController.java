package com.example.CSC131Project.Controller;

import com.example.CSC131Project.Model.Movie;
import com.example.CSC131Project.Model.MovieRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class RenderFormController
{
    @Autowired
    public MovieRepository movieRepository;

    @GetMapping("/academyAward/findaward")
    public String academyAwardFormHandler()
    {
        return "/academyAward/academyAwardForm";
    }

    @GetMapping("/addMovie")
    public String addMovie(Model model){
        model.addAttribute("movie",new Movie());
        return "add_form";
    }

    @PostMapping("/editMovieForm")
    public String editMovieForm(@Valid Movie editedMovie, Model model){
        Movie movie = movieRepository.find1Movie(editedMovie.getTitle());
        model.addAttribute("movie",movie);
        return "edit_movie_form";
    }
}
