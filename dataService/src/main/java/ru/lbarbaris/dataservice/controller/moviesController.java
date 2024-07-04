package ru.lbarbaris.dataservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.lbarbaris.dataservice.entity.Movie;
import ru.lbarbaris.dataservice.entity.MovieResponse;
import ru.lbarbaris.dataservice.service.MovieService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class moviesController {

    @Autowired
    private MovieService movieService;


    public String getMovie(@PathVariable String name){
        return null;
    }





    public String getUserData(){
        return null;
    }


    @PostMapping
    public String saveMovie(@RequestBody MovieResponse movieResponse){
        movieService.save(movieResponse);
        return movieResponse.toString();
    }

    @GetMapping
    public List<Movie> getAll() {
        return movieService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        movieService.deleteById(id);
    }

    @GetMapping("/{id}")
    public Optional<Movie> getById(@PathVariable("id") Long id) {
        return movieService.findById(id);
    }
}
