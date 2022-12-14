package com.example.CSC131Project.Controller;

import com.example.CSC131Project.Model.Movie;
import com.example.CSC131Project.Model.MovieRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;

@Controller
public class RenderFormController
{

    @GetMapping("/academyAward/findaward")
    public String academyAwardFormHandler()
    {
        return "/academyAward/academyAwardForm";
    }

    @GetMapping("/academyAward/findActor")
    public String searchActorFormHandler()
    {
        return "/academyAward/searchActorForm";
    }

    @GetMapping("/addMovie")
    public String addMovie(Model model){
        model.addAttribute("movie",new Movie());
        return "/Movie/add_form";
    }

    @GetMapping("/searchForAMovie")
    public String searchMovie(Model model){
        model.addAttribute("movie", new Movie());
        return "/Movie/search";
    }


}
