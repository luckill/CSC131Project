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
    private String MovieID;
    private String Title;
    private String Director;
    private int Year;
    private String Language;

     public Movie(){
    }//Default Constructor
    public Movie(String M, String T, String D, int Y, String L){
         MovieID = M;
         Title = T;
         Director = D;
         Year = Y;
         Language = L;
    }//Constructor

    public int getID(){
         return ID;
    }

    public String getMovieID(){
        return MovieID;
    }
    public void setMovieID(String i){
        this.MovieID = i;
    }
    public String getTitle(){
        return Title;
    }
    public void setTitle(String i){
        this.Title = i;
    }
    public String getDirector(){
        return Director;
    }
    public void setDirector(String i){
        this.Director = i;
    }
    public int getYear(){
        return Year;
    }
    public void setYear(int i){
        this.Year = i;
    }
    public String getLanguage(){
        return Language;
    }
    public void setLanguage(String i){
        this.Language = i;
    }

}//Movie class
