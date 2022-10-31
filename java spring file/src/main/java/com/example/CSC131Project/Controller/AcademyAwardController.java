package com.example.CSC131Project.Controller;

import com.example.CSC131Project.*;
import com.example.CSC131Project.Model.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/academyAward")
public class AcademyAwardController
{
    @Autowired
    public MovieRepository movieRepository;

    @GetMapping("/actor/{year}")
    public String FindActorAwardByYear(@PathVariable("year") int year)
    {
        System.out.println(year);
        System.out.println();
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
                obj.put("Award", s.getCategory());
                obj.put("Year", s.getYearOfAward());
                obj.put("file tile", s.getFilm());
                array.add(obj);
                if (movieRepository.findByTitle(title).isEmpty())
                {
                    String temp = ApiCommunicator.getRequest(title);
                    JSONParser parser = new JSONParser();
                    JSONObject json = (JSONObject) parser.parse(temp);

                    String filmTitle = (String) json.get("Title");
                    String director = (String) json.get("Director");
                    String language = (String) json.get("Language");
                    String movieID = (String) json.get("imdbID");
                    int yearOfFilm = Integer.parseInt((String)json.get("Year"));

                    Movie movie = new Movie(movieID, filmTitle, director, yearOfFilm, language);
                    movieRepository.save(movie);
                }
            }
            result = array.toJSONString();
        }
        catch (ParseException ex)
        {
            ex.printStackTrace();
        }
        return result;
    }
}


