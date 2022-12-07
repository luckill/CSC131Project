package com.example.CSC131Project.Controller;

import com.example.CSC131Project.ApiConfiguration;
import com.example.CSC131Project.Model.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.*;

import javax.validation.*;
import java.util.*;

@Controller
//@Component
//@Service
@RequestMapping("/academyAward")
public class AcademyAwardController
{
    @Autowired
    public MovieRepository movieRepository;
    private final ApiConfiguration apiConfiguration;
    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public AcademyAwardController(ApiConfiguration apiConfiguration)
    {
        this.apiConfiguration = apiConfiguration;
    }

    @GetMapping("/actor")
    public String FindActorAwardByYear(@RequestParam("year") String year, Model model, @Valid AcademyAwardForm academyAwardForm, BindingResult bindingResult)
    {
        int awardYear = Integer.parseInt(year);
        /*if (awardYear < 1928 || awardYear > 2020 )
        {
            //redirectAttributes.addFlashAttribute("error", "No academy award data fro specific award year found!!!");
            //return "redirect:index";
        }*/

        if (bindingResult.hasErrors())
        {
            return "academyAward/academyAwardForm";
        }
        List<AcademyAward> awardList = AppStartUpListener.dataMap.
                values().
                stream().
                filter(academyAward -> academyAward.getYearOfAward() == awardYear && academyAward.isWinner() && academyAward.getCategory().contains("ACTOR")).
                toList();
        //JSONArray array = new JSONArray();
        model.addAttribute("actorAwardList", awardList);

        model.addAttribute("movies", filmProcessor(awardList));
        //result = array.toJSONString();
        return "/academyAward/actorAndBestPicture";
    }

    @GetMapping("AwardWinnerAndNominee")
    public String getAwardWinnerAndNomineeByYear(@RequestParam("year") int year, Model model) {
        List<AcademyAward> awardList = AppStartUpListener.dataMap.values().stream().filter(academyAward -> academyAward.getYearOfAward() == year).toList();
        List<AcademyAward> winnerList = new ArrayList<>();
        List<AcademyAward> nomineeList = new ArrayList<>();
        model.addAttribute("bestPictureAwardList", awardList);
        /*JSONArray array = new JSONArray();
        JSONArray nominee = new JSONArray();
        JSONArray winner = new JSONArray();*/
        for (AcademyAward award : awardList)
        {
            /*JSONObject obj = new JSONObject();
            obj.put("Award", s.getCategory());
            obj.put("Year", s.getYearOfAward());
            obj.put("film tile", s.getFilm());*/

            if (award.isWinner())
            {
                //winner.add(obj);
                winnerList.add(award);
            }
            else
            {
                nomineeList.add(award);
            }
        }
        model.addAttribute("winners", winnerList);
        model.addAttribute("nominees", nomineeList);
        //array.add(winner);
        //array.add(nominee);
        //return array.toJSONString();
        return "/academyAward/awardWinnerAndNominee";
    }

    private List<Movie> filmProcessor(List<AcademyAward> awardList)
    {
        //JSONArray array = new JSONArray();
        List<Movie> movieList = new ArrayList<>();
        try
        {
            for (AcademyAward s : awardList)
            {
                String title = s.getFilm();
                /*JSONObject obj = new JSONObject();
                JSONObject movieObj = new JSONObject();
                obj.put("Award", s.getCategory());
                obj.put("Year", s.getYearOfAward());
                obj.put("film tile", s.getFilm());*/
                //array.add(obj);
                List<Movie> list = movieRepository.findByTitle(title);
                if (list.isEmpty())
                {
                    ApiCommunicator communicator = new ApiCommunicator();
                    String temp = communicator.getRequest(title, apiConfiguration.AuthorizeToke());
                    Movie movie = objectMapper.readValue(temp, Movie.class);
                    movieRepository.save(movie);
                    movieList.add(movie);
                }
                else
                {
                    movieList.addAll(list);
                }
                /*else
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
                array.add(movieObj);*/
            }

        //List<Movie> movieList = (List<Movie>)movieRepository.findAll();
            //result = array.toJSONString();
        }
        catch (JsonMappingException ex)
        {
            ex.printStackTrace();
        }
        catch (JsonProcessingException e)
        {
            throw new RuntimeException(e);
        }
        return movieList;
    }

}



