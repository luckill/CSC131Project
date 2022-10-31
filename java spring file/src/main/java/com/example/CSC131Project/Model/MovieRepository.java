package com.example.CSC131Project.Model;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.*;

import java.util.*;

public interface MovieRepository extends CrudRepository<Movie, String>
{
    @Query("SELECT m FROM Movie m WHERE m.title=?1")
    List<Movie> findByTitle(String title);
}