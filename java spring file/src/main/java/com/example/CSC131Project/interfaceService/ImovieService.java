package com.example.CSC131Project.interfaceService;

import com.example.CSC131Project.Model.Movie;

import java.util.List;
import java.util.Optional;

public interface ImovieService {
    public List<Movie>list();
    public Optional<Movie>listId(int id);
    public int save(Movie m);
    public void delete(int id);
}
