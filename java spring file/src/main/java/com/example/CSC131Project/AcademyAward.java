package com.example.CSC131Project;

import javax.persistence.*;
import java.util.*;

@Entity
public class AcademyAward
{
    @Id
    @GeneratedValue
    private int id;
    private int yerOfFilm;
    private int yearOfAward;
    private int ceremony;
    private String category;
    private String name;
    private String film;
    private boolean winner;

    public AcademyAward()
    {

    }

    public AcademyAward( int yerOfFilm, int yearOfAward, int ceremony, String category, String name, String film, boolean winner)
    {
        this.yerOfFilm = yerOfFilm;
        this.yearOfAward = yearOfAward;
        this.ceremony = ceremony;
        this.category = category;
        this.name = name;
        this.film = film;
        this.winner = winner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYerOfFilm() {
        return yerOfFilm;
    }

    public void setYerOfFilm(int yerOfFilm) {
        this.yerOfFilm = yerOfFilm;
    }

    public int getYearOfAward() {
        return yearOfAward;
    }

    public void setYearOfAward(int yearOfAward) {
        this.yearOfAward = yearOfAward;
    }

    public int getCeremony() {
        return ceremony;
    }

    public void setCeremony(int ceremony) {
        this.ceremony = ceremony;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademyAward that = (AcademyAward) o;
        return id == that.id && yerOfFilm == that.yerOfFilm && yearOfAward == that.yearOfAward && ceremony == that.ceremony && winner == that.winner && category.equals(that.category) && name.equals(that.name) && film.equals(that.film);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(yerOfFilm, yearOfAward);
    }
}
