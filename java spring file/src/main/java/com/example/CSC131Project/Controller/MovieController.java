package com.example.CSC131Project.Controller;

import com.example.CSC131Project.ApiConfiguration;
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
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component
@Service
@RequestMapping("/movies")
public class MovieController
{
    @Autowired
    MovieRepository movieRepository;
    private final ApiConfiguration apiConfiguration;
    ApiCommunicator IMDBApi = new ApiCommunicator();

    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public MovieController(ApiConfiguration apiConfiguration)
    {
        this.apiConfiguration = apiConfiguration;
    }


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
        String json = IMDBApi.getRequest(title, apiConfiguration.AuthorizeToke());
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
    @PutMapping("/updateMovie/{title}")
    public String updateMovie(@RequestBody String body, @PathVariable String title) throws JsonProcessingException
    {
        Movie movie = movieRepository.find1Movie(title);
        System.out.println(body);
        if(movie != null && body != null)
        {
            Movie temp = objectMapper.readValue(body, Movie.class);
            //return objectMapper.writeValueAsString(temp); //change

            if(temp.getTitle()!= null)
            {
                movie.setTitle(temp.getTitle());
            }
            if(temp.getDirector() != null)
            {
                movie.setDirector(temp.getDirector());
            }
            if(temp.getYear() != 0)
            {
                movie.setYear(temp.getYear());
            }
            if (temp.getLanguage() != null)
            {
                movie.setLanguage(temp.getLanguage());
            }
            movieRepository.save(movie);
            return objectMapper.writeValueAsString(movie);
        }
        else
        {
            return "Movie not found";
        }

    } //update movie closing


    @Modifying
    @DeleteMapping("/delete/{ID}")
    public String deleteMovieById(@PathVariable int ID) throws JsonProcessingException {
        List<Movie> movies = movieRepository.findByID(ID);
        if(movies.size() != 0){
            movieRepository.deleteByID(ID);
            return objectMapper.writeValueAsString(movies.get(0));
        }
        String error = "{\"Error\": \"Movie id does not exist\"}";
        return objectMapper.writeValueAsString(objectMapper.readTree(error));
    }

}