package com.example.CSC131Project.Controller;

import com.example.CSC131Project.Model.Movie;
import com.example.CSC131Project.Model.MovieRepository;
import com.example.CSC131Project.interfaceService.ImovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class WebController {
    @Autowired
    public MovieRepository movieRepository;
    @Autowired
    public ImovieService service;
    @GetMapping("/list")
    public String list(Model model) throws JsonProcessingException {
        List<Movie> movies =movieRepository.list();
        model.addAttribute("movies",movies);
        return "index";
    }

    @GetMapping("/new")
    public String add(Model model){
        model.addAttribute("movie",new Movie());
        return "AddForm";
    }
    @GetMapping("/search")
    public String search(Model model){
        model.addAttribute("Title",new Movie());
        return "search";
    }
    @GetMapping("/searchResult")
    public String searchResult(@Valid String title,Model model){
        Movie movie = movieRepository.find1Movie(title);
        model.addAttribute("movie",movie);
        return "result";
    }
    @PostMapping("/save")
    public String save(@Valid Movie m, Model model){
        movieRepository.save(m);
        return "redirect:/list";
    }

}
