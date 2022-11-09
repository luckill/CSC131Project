package com.example.CSC131Project.interfaces;

import com.example.CSC131Project.Model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovie extends CrudRepository<Movie,Integer> {
}
