package com.example.CSC131Project.Controller;


import com.example.CSC131Project.ApiConfiguration;
import com.example.CSC131Project.Model.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
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
    public String FindActorAwardByYear(@RequestParam("year") int year, Model model)
    {
        //int awardYear = Integer.parseInt(year);
        if (year < 1928 || year > 2020 )
        {
                model.addAttribute("errorMessage","Invalid Year!!! - no best actor award data from this year");
                return "/academyAward/academyAwardForm";
        }

        List<AcademyAward> awardList = AppStartUpListener.dataMap.
                values().
                stream().
                filter(academyAward -> academyAward.getYearOfAward() == year && academyAward.isWinner() && academyAward.getCategory().contains("ACTOR")).
                toList();

        model.addAttribute("actorAwardList", awardList);

        model.addAttribute("movies", filmProcessor(awardList));
        return "/academyAward/actorAndBestPicture";
    }

    @GetMapping("AwardWinnerAndNominee")
    public String getAwardWinnerAndNomineeByYear(@RequestParam("year") int year, Model model) {
        if (year < 1928 || year > 2020 )
        {
            model.addAttribute("errorMessage","Invalid Year!!! - no academy award data from this year");
            return "/academyAward/academyAwardForm";
        }
        List<AcademyAward> awardList = AppStartUpListener.dataMap.
                values().
                stream().
                filter(academyAward -> academyAward.getYearOfAward() == year).
                toList();
        List<AcademyAward> winnerList = new ArrayList<>();
        List<AcademyAward> nomineeList = new ArrayList<>();
        model.addAttribute("bestPictureAwardList", awardList);
        for (AcademyAward award : awardList)
        {
            if (award.isWinner())
            {
                winnerList.add(award);
            }
            else
            {
                nomineeList.add(award);
            }
        }
        model.addAttribute("winners", winnerList);
        model.addAttribute("nominees", nomineeList);
        return "/academyAward/awardWinnerAndNominee";
    }

    @GetMapping("BestPictureByYear")
    public String OscarBestPictureByYear(@RequestParam("year") int year, Model model)
    {

        if (year < 1928 || year > 2020 )
        {
            model.addAttribute("errorMessage","Invalid Year!!! - no best actor award data from this year");
            return "/academyAward/academyAwardForm";
        }

        List<AcademyAward> awardList = AppStartUpListener.dataMap.
                values().
                stream().
                filter(academyAward -> academyAward.getYearOfAward() == year && academyAward.isWinner() && (academyAward.getCategory().contains("BEST PICTURE") || academyAward.getCategory().contains("BEST MOTION PICTURE"))).
                toList();
        if(awardList.isEmpty())
        {
            String error = "no related academy award data";
            model.addAttribute("error", error);
            return "error";
        }

        model.addAttribute("actorAwardList", awardList);

        model.addAttribute("movies", filmProcessor(awardList));

        return "/academyAward/actorAndBestPicture";
    }

    @GetMapping("searchActor")
    public String searchActor(@RequestParam String actorFirstName, @RequestParam String actorLastName, Model model)
    {
        String actorName = actorFirstName +  " " + actorLastName;
        List<AcademyAward> awardList = AppStartUpListener.dataMap.
                values().
                stream().
                filter(academyAward -> academyAward.getName().equalsIgnoreCase(actorName) || academyAward.getName().contains(actorName)).
                toList();
        if(awardList.isEmpty())
        {
            model.addAttribute("message","No related academy award data found!!!");
            return "/academyAward/searchActorForm";
        }
        model.addAttribute("awardList", awardList);
        return "/academyAward/searchActor";
    }

    private List<Movie> filmProcessor(List<AcademyAward> awardList)
    {
        List<Movie> movieList = new ArrayList<>();
        try
        {
            for (AcademyAward s : awardList)
            {
                String title = s.getFilm();
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
            }
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



