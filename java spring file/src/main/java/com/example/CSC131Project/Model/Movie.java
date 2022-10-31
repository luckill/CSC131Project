package com.example.CSC131Project.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int ID;
    private String movieID;
    private String title;
    private String director;
    private int year;
    private String language;

     public Movie(){
    }//Default Constructor
    public Movie(String M, String T, String D, int Y, String L){
         movieID = M;
         title = T;
         director = D;
         year = Y;
         language = L;
    }//Constructor

    public int getID(){
         return ID;
    }

    public String getMovieID(){
        return movieID;
    }
    public void setMovieID(String i){
        this.movieID = i;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String i){
        this.title = i;
    }
    public String getDirector(){
        return director;
    }
    public void setDirector(String i){
        this.director = i;
    }
    public int getYear(){
        return year;
    }
    public void setYear(int i){
        this.year = i;
    }
    public String getLanguage(){
        return language;
    }
    public void setLanguage(String i){
        this.language = i;
    }

}//Movie class
