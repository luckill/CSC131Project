package com.example.CSC131Project.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name="movie")

public class Movie {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    private String movieID;
    private String title;
    private String director;
    private int year;
    private String language;

    public Movie(){
    }//Default Constructor
    public Movie(String M, String T, String D, int Y, String L){
        super();
        //this.id=id;
        movieID = M;
        title = T;
        director = D;
        year = Y;
        language = L;
    }//Constructor


    public int getid(){
        return id;
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
