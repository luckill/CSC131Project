package com.example.CSC131Project.Controller;

import com.example.CSC131Project.ApiConfiguration;
import com.example.CSC131Project.Model.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
//@Component
//@Service
@RequestMapping("/academyAward")
public class AcademyAwardController
{
    @Autowired
    public MovieRepository movieRepository;
    private final ApiConfiguration apiConfiguration;

    public AcademyAwardController(ApiConfiguration apiConfiguration)
    {
        this.apiConfiguration = apiConfiguration;
    }

    @GetMapping("/actor/{year}")
    public String FindActorAwardByYear(@PathVariable("year") int year)
    {
        String result = "";
        try
        {
            List<AcademyAward> awardList = AppStartUpListener.dataMap.
                    values().
                    stream().
                    filter(academyAward -> academyAward.getYearOfAward() == year && academyAward.isWinner() && academyAward.getCategory().contains("ACTOR")).
                    toList();
            JSONArray array = new JSONArray();

            for (AcademyAward s : awardList)
            {
                String title = s.getFilm();
                JSONObject obj = new JSONObject();
                JSONObject movieObj = new JSONObject();
                obj.put("Award", s.getCategory());
                obj.put("Year", s.getYearOfAward());
                obj.put("film tile", s.getFilm());
                array.add(obj);
                if (movieRepository.findByTitle(title).isEmpty())
                {
                    ApiCommunicator communicator = new ApiCommunicator();
                    String temp = communicator.getRequest(title, apiConfiguration.AuthorizeToke());
                    JSONParser parser = new JSONParser();
                    JSONObject json = (JSONObject) parser.parse(temp);

                    String filmTitle = (String) json.get("Title");
                    String director = (String) json.get("Director");
                    String language = (String) json.get("Language");
                    String movieID = (String) json.get("imdbID");
                    int yearOfFilm = Integer.parseInt((String)json.get("Year"));
                    movieObj.put("title", filmTitle);
                    movieObj.put("Director", director);
                    movieObj.put("Language", language);
                    movieObj.put("imdbID", movieID);
                    movieObj.put("Year", yearOfFilm);

                    Movie movie = new Movie(movieID, filmTitle, director, yearOfFilm, language);
                    movieRepository.save(movie);
                }
                else
                {
                    List<Movie> movieList = movieRepository.findByTitle(title);
                    for(Movie movie: movieList)
                    {
                        movieObj.put("title", movie.getTitle());
                        movieObj.put("Director", movie.getDirector());
                        movieObj.put("Language", movie.getLanguage());
                        movieObj.put("imdbID", movie.getMovieID());
                        movieObj.put("Year", movie.getYear());
                    }
                }
                array.add(movieObj);
            }
            result = array.toJSONString();
        }
        catch (ParseException ex)
        {
            ex.printStackTrace();
        }
        return result;
    }

    @GetMapping("AwardWinnerAndNominee/{year}")
    public String getAwardWinnerAndNomineeByYear(@PathVariable("year") int year)
    {
        List<AcademyAward> awardList = AppStartUpListener.dataMap.values().stream().filter(academyAward -> academyAward.getYearOfAward() == year).toList();
        JSONArray array = new JSONArray();
        JSONArray nominee = new JSONArray();
        JSONArray winner = new JSONArray();
        for (AcademyAward s: awardList)
        {
            JSONObject obj = new JSONObject();
            obj.put("Award", s.getCategory());
            obj.put("Year", s.getYearOfAward());
            obj.put("film tile", s.getFilm());

            if (s.isWinner())
            {
                winner.add(obj);
            }
            else
            {
                nominee.add(obj);
            }
        }
        array.add(winner);
        array.add(nominee);
        return array.toJSONString();
    }
}


