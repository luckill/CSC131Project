package com.example.CSC131Project.Controller;

import com.example.CSC131Project.ApiConfiguration;
import com.example.CSC131Project.Model.ApiCommunicator;
import com.example.CSC131Project.Model.Movie;
import com.example.CSC131Project.Model.MovieRepository;
import com.example.CSC131Project.interfaceService.ImovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.CSC131Project.ApiConfiguration;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class WebController {
    @Autowired
    public MovieRepository movieRepository;
    @Autowired
    public ImovieService service;

    private final ApiConfiguration apiConfiguration;
    public WebController(ApiConfiguration apiConfiguration)
    {
        this.apiConfiguration = apiConfiguration;
    }
    ApiCommunicator iMDBApi = new ApiCommunicator();
    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    @GetMapping("/listAllMovies")
    public String list(Model model) throws JsonProcessingException {
        List<Movie> movies =movieRepository.list();
        model.addAttribute("movies",movies);
        return "list_of_all_movies";
    }

    @GetMapping("/addMovie")
    public String addMovie(Model model){
        model.addAttribute("movie",new Movie());
        return "add_form";
    }
    @GetMapping("/searchForAMovie")
    public String searchMovie(Model model){
        model.addAttribute("movie", new Movie());
        return "search";
    }
    @GetMapping("/searchResultOfMovie")
    public String searchResultOfMovie(@Valid Movie inputMovie,Model model) throws JsonProcessingException {
        Movie movie =movieRepository.find1Movie(inputMovie.getTitle());
        if (movie != null)
        {
            model.addAttribute("movie",movie);
        }
        else
        {
            String json = iMDBApi.getRequest(inputMovie.getTitle(), apiConfiguration.AuthorizeToke());
            if (!json.contains("Movie not found!")) {
                Movie thisMovie = objectMapper.readValue(json, Movie.class);
                thisMovie = movieRepository.save(thisMovie);
                model.addAttribute("movie", thisMovie);
            }
        }
        return "result";
    }
    @PostMapping("/editMovieForm")
    public String editMovieForm(@Valid Movie editedMovie, Model model){
        Movie movie = movieRepository.find1Movie(editedMovie.getTitle());
        model.addAttribute("movie",movie);
        return "edit_movie_form";
    }
    @PostMapping("/editMovie")
    public String editMovie(@Valid Movie editedMovie, Model model) {
        Movie movie = movieRepository.find1MovieByIMBDId(editedMovie.getMovieID());
        if(movie != null && editedMovie != null)
        {
            if(editedMovie.getTitle() != movie.getTitle())
            {
                movie.setTitle(editedMovie.getTitle());
            }
            if(editedMovie.getDirector() != movie.getDirector())
            {
                movie.setDirector(editedMovie.getDirector());
            }
            if(editedMovie.getYear() != movie.getYear())
            {
                movie.setYear(editedMovie.getYear());
            }
            if (editedMovie.getLanguage() != movie.getLanguage())
            {
                movie.setLanguage(editedMovie.getLanguage());
            }

            movie = movieRepository.save(movie);
            model.addAttribute("movie",movie);
        }
        return "result";
    }
    @PostMapping("/deleteAMovie")
    public String deleteAMovie(@Valid Movie deleteMovie, Model model){
        movieRepository.deleteByTitle(deleteMovie.getTitle());
        model.addAttribute("movie", deleteMovie);
        return "redirect:/listAllMovies";
    }
    @PostMapping("/saveAMovie")
    public String saveAMovie(@Valid Movie saveMovie, Model model){
        movieRepository.save(saveMovie);
        return "redirect:/listAllMovies";
    }

}
