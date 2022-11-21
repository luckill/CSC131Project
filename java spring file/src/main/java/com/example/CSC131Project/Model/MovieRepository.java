package com.example.CSC131Project.Model;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.*;
@Repository
public interface MovieRepository extends CrudRepository<Movie, String> {
    @Query("SELECT m FROM Movie m")
    public List<Movie>list();
    @Query("SELECT m FROM Movie m WHERE m.title=?1")
    List<Movie> findByTitle(String title);

    @Query("SELECT m FROM Movie m WHERE m.ID=?1")
    List<Movie> findByID(int ID);


    @Query("SELECT m FROM Movie m WHERE m.title=?1")
    Movie find1Movie(String title);

    @Query("SELECT m FROM Movie m WHERE m.movieID=?1")
    Movie find1MovieByIMBDId(String ID);

    @Transactional
    @Modifying
    @Query("DELETE FROM Movie m WHERE m.ID=?1")
    void deleteByID(int ID);

    @Transactional
    @Modifying
    @Query("DELETE FROM Movie m WHERE m.title=?1")
    void deleteByTitle(String title);



}