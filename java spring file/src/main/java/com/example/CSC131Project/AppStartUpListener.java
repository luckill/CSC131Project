package com.example.CSC131Project;

import com.example.CSC131Project.Model.*;
import org.springframework.context.*;
import org.springframework.context.event.*;
import org.springframework.stereotype.*;
import java.io.*;
import java.util.*;

@Component
public class AppStartUpListener implements ApplicationListener<ContextRefreshedEvent>
{
    public static Map<Integer, AcademyAward> dataMap;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        int count = 0;
        dataMap = new HashMap<>();
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
                dataMap.put(count, award);
                count++;
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
    }
}
