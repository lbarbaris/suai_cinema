package ru.lbarbaris.webservice.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.lbarbaris.webservice.dataServiceDAO.MovieService;
import ru.lbarbaris.webservice.dto.Movie;
import ru.lbarbaris.webservice.dto.MovieResponse;

import java.util.List;

@Log4j2
@Controller
public class ImageController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/test")
    public String showImages2(Model model) {
        List<Movie> movies = movieService.getMovies("http://localhost:8081/movies");
        log.info(movies);
        model.addAttribute("Movies", movies);
        return "images";



/*        List<String> imageUrls = List.of(
                    "http://image.tmdb.org/t/p/w500/hxP5zubomplKlOZaXmgHd1P9WsN.jpg"
        );
        model.addAttribute("imageUrls", imageUrls);
        return "images";*/
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
