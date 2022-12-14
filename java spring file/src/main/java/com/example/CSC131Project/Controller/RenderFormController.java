package com.example.CSC131Project.Controller;

import com.example.CSC131Project.Model.Movie;
import com.example.CSC131Project.Model.MovieRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    @GetMapping("/searchForAMovie")
    public String searchMovie(Model model){
        model.addAttribute("movie", new Movie());
        return "/Movie/search";
    }
}
