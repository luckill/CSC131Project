package com.example.CSC131Project.Model;

import java.util.*;


public class AcademyAward
{
    private int yearOfFilm;
    private int yearOfAward;
    private int ceremony;
    private String category;
    private String name;
    private String film;
    private boolean winner;

    public AcademyAward()
    {
    }

    public AcademyAward(int yearOfFilm, int yearOfAward, int ceremony, String category, String name, String film, boolean winner)
    {
        this.yearOfFilm = yearOfFilm;
        this.yearOfAward = yearOfAward;
        this.ceremony = ceremony;
        this.category = category;
        this.name = name;
        this.film = film;
        this.winner = winner;
    }

    public int getYearOfFilm()
    {
        return yearOfFilm;
    }

    public void setYerOfFilm(int yearOfFilm)
    {
        this.yearOfFilm = yearOfFilm;
    }

    public int getYearOfAward()
    {
        return yearOfAward;
    }

    public void setYearOfAward(int yearOfAward)
    {
        this.yearOfAward = yearOfAward;
    }

    public int getCeremony()
    {
        return ceremony;
    }

    public void setCeremony(int ceremony)
    {
        this.ceremony = ceremony;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getFilm()
    {
        return film;
    }

    public void setFilm(String film)
    {
        this.film = film;
    }

    public boolean isWinner()
    {
        return winner;
    }

    public void setWinner(boolean winner)
    {
        this.winner = winner;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof AcademyAward)
        {
            AcademyAward other = (AcademyAward) obj;
            return this.yearOfFilm == other.yearOfFilm &&
                    this.yearOfAward == other.yearOfAward &&
                    this.ceremony == other.ceremony &&
                    this.category.equals(other.category) &&
                    this.film.equals(other.film) &&
                    this.winner == other.winner;
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(yearOfFilm, yearOfAward);
    }
}