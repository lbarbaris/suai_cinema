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

    @PostMapping
    public Movie saveMovie(@RequestBody MovieResponse movieResponse){
        return movieService.save(movieResponse);
    }

    @PostMapping("/deleteByMovie")
    public void deleteMovie(@RequestBody MovieResponse movieResponse){
        movieService.delete(movieResponse);
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

    @GetMapping("/search/getAllByUserdataUsername")
    public List<Movie> getAllByUsername(@RequestParam("username") String username){
        System.out.println(movieService.getAllByUserdataUsername(username));
        System.out.println(username);
        return movieService.getAllByUserdataUsername(username);
    }
}
