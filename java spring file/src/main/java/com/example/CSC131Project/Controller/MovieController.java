package com.example.CSC131Project.Controller;

import com.example.CSC131Project.ApiConfiguration;
import com.example.CSC131Project.Model.ApiCommunicator;
import com.example.CSC131Project.Model.Movie;
import com.example.CSC131Project.Model.MovieRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Random;

@Controller
public class MovieController {
    @Autowired
    public MovieRepository movieRepository;


    private final ApiConfiguration apiConfiguration;
    public MovieController(ApiConfiguration apiConfiguration)
    {
        this.apiConfiguration = apiConfiguration;
    }
    ApiCommunicator iMDBApi = new ApiCommunicator();
    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @GetMapping("/searchResult/")
    public String searchResultOfMovie(@Valid Movie inputMovie,Model model) throws JsonProcessingException {
        Movie movie =movieRepository.find1Movie(inputMovie.getTitle());
        if (movie != null)
        {
            model.addAttribute("movie",movie);
            return "/Movie/result";
        }
        else
        {
            String json = iMDBApi.getRequest(inputMovie.getTitle(), apiConfiguration.AuthorizeToke());
            if (!json.contains("Movie not found!")) {
                Movie thisMovie = objectMapper.readValue(json, Movie.class);
                Movie tempMovie = movieRepository.find1MovieByIMBDId(thisMovie.getMovieID());
                while(tempMovie != null && thisMovie.getMovieID() == tempMovie.getMovieID())
                {
                    String id = this.createUniqueId(tempMovie);
                    tempMovie.setMovieID(id);
                    tempMovie = movieRepository.save(tempMovie);
                }
                thisMovie = movieRepository.save(thisMovie);

                model.addAttribute("movie", thisMovie);
                return "/Movie/result";
            }
        }
        String error =  "No such movie does not exist";
        model.addAttribute("error", error);
        return "error";
    }

    @PostMapping("/editMovie/{title}")
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

    @PostMapping("/editMovieForm")
    public String editMovieForm(@Valid Movie editedMovie, Model model){
        Movie movie = movieRepository.find1Movie(editedMovie.getTitle());
        model.addAttribute("movie",movie);
        return "edit_movie_form";
    }

    @PostMapping("/saveAMovie")
    public String saveAMovie(@Valid Movie saveMovie, Model model) throws JsonProcessingException {
        if(saveMovie.getTitle() == "")
        {
            String error = "Cannot add without a Title";
            model.addAttribute("error", error);
            return "error";
        }
        Movie tempMovie = movieRepository.find1Movie(saveMovie.getTitle());
        if(tempMovie != null )
        {
           String error =  "Movie already exists";
           model.addAttribute("error", error);
           return "error";
        }

        String id = this.createUniqueId(saveMovie);
        saveMovie.setMovieID(id);
        saveMovie = movieRepository.save(saveMovie);
        return "redirect:/listAllMovies";

    }

    @GetMapping("/listAllMovies")
    public String list(Model model) throws JsonProcessingException {
        List<Movie> movies =movieRepository.list();
        model.addAttribute("movies",movies);
        return "list_of_all_movies";
    }

    @GetMapping("/recommend")
    public String recommend(Model model) throws JsonProcessingException {
        List<Movie> movies =movieRepository.list();
        Random random = new Random();
        Movie movie = movies.get(random.nextInt(movies.size()));
        movies.remove(movie);
        model.addAttribute("movies",movies);
        model.addAttribute("movie",movie);
        return "recommend";
    }


    public String createUniqueId(Movie movie)
    {
        Random random = new Random();
        String id = "ff" + String.format("%07d", random.nextInt(10000));
        Movie tempMovie = movieRepository.find1MovieByIMBDId(id);
        while(tempMovie != null && tempMovie.getMovieID() == id){
            id = "ff" + String.format("%07d", random.nextInt(10000));
        }
        return id;
    }
}