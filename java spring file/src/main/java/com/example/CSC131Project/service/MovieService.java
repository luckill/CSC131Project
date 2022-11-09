package com.example.CSC131Project.service;

import com.example.CSC131Project.Model.Movie;
import com.example.CSC131Project.interfaceService.ImovieService;
import com.example.CSC131Project.interfaces.IMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements ImovieService {
    @Autowired
    private IMovie data;

    @Override
    public List<Movie> list() {
        return (List<Movie>)data.findAll();
    }

    @Override
    public Optional<Movie> listId(int id) {
        return Optional.empty();
    }

    @Override
    public int save(Movie m) {
        int res=0;
        Movie movie=data.save(m);
        if (!movie.equals(null)) {
            res=1;
        }
        return res;
    }



    @Override
    public void delete(int id) {

    }
}
