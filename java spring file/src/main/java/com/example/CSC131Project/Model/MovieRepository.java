package com.example.CSC131Project.Model;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public interface MovieRepository extends CrudRepository<Movie, String> {
    @Query("SELECT m FROM Movie m WHERE m.title=?1")
    List<Movie> findByTitle(String title);

    @Query("SELECT m FROM Movie m WHERE m.movieID=?1")
    List<Movie> findByMovieID(String movieID);

}