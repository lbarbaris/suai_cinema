package ru.lbarbaris.webservice.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.lbarbaris.webservice.service.dataService.MovieService;
import ru.lbarbaris.webservice.service.dataService.UserDataService;
import ru.lbarbaris.webservice.dto.dataService.Movie;
import ru.lbarbaris.webservice.dto.dataService.UserData;
import ru.lbarbaris.webservice.service.site.SiteService;

import java.util.List;
import java.util.Map;

@Log4j2
@Controller
public class webController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private SiteService siteService;

    @GetMapping("/myMovies")
    public String myMovies(Model model){
        String authName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserData userData = userDataService.getUserData(authName);
        List<Movie> movies = movieService.getMoviesList(authName);
        model.addAllAttributes(Map.of("Movies", movies, "User", userData));
        return "myMovies";
    }

    @GetMapping("/allMovies")
    public String allMovies(Model model){
        String authName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserData userData = userDataService.getUserData(authName);
        model.addAllAttributes(Map.of("Movies", siteService.getMoviesList(), "User", userData));
        return "allMovies";
    }

    @PostMapping("/addToMyMovies")
    public String addToMyMovies(@RequestParam(name = "name") String name,
                                @RequestParam(name = "rating") float rating,
                                @RequestParam(name = "description") String description,
                                @RequestParam(name = "imageurl") String imageurl) {
        Movie movie = new Movie(imageurl, name, description, rating);
        String authName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserData userData = userDataService.getUserData(authName);
        movieService.saveMovie(movie, userData);
        return "redirect:/myMovies";
    }
/*    @GetMapping("/profile/{username}")
    public String showImages(Model model, @PathVariable String username) {
        List<Movie> list = movieService.getMovies("http://localhost:8081/movies").collectList().block();
        System.out.println(list);
        return list.toString();

*//*        List<String> imageUrls = List.of(
                    "http://image.tmdb.org/t/p/w500/hxP5zubomplKlOZaXmgHd1P9WsN.jpg"
        );
        model.addAttribute("imageUrls", imageUrls);
        return "images";*//*
    }*/
}
