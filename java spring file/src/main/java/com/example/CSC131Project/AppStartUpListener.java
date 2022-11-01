package com.example.CSC131Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.*;
import org.springframework.context.event.*;
import org.springframework.stereotype.*;

import java.io.*;
import java.sql.*;
import java.util.*;

@Component
public class AppStartUpListener implements ApplicationListener<ContextRefreshedEvent>
{
    @Autowired
    AcademyAwardRepository academyAwardRepository;

    public AppStartUpListener(AcademyAwardRepository academyAwardRepository)
    {
        this.academyAwardRepository = academyAwardRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        if (!academyAwardRepository.findAll().iterator().hasNext())
        {
            try (Scanner fileScan = new Scanner(new FileInputStream("the_oscar_award.csv")))
            {
                fileScan.nextLine();
                while (fileScan.hasNext())
                {
                    String line = fileScan.nextLine();
                    Scanner lineScanner = new Scanner(line);
                    lineScanner.useDelimiter(",");

                    int yerOfFilm = Integer.parseInt(lineScanner.next());
                    int yearOfAward = Integer.parseInt(lineScanner.next());
                    int ceremony = Integer.parseInt(lineScanner.next());
                    String category = lineScanner.next();
                    String name = lineScanner.next();
                    String film = lineScanner.next();
                    boolean winner;
                    winner = lineScanner.next().trim().equals("TRUE");
                    AcademyAward award = new AcademyAward(yerOfFilm, yearOfAward, ceremony, category, name, film, winner);
                    academyAwardRepository.save(award);
                }
            }
            catch (FileNotFoundException e)
            {
                System.out.println("File not found");
            }
        }
    }
}
