package ru.lbarbaris.webservice.service.dataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.lbarbaris.webservice.dto.dataService.Movie;
import ru.lbarbaris.webservice.dto.dataService.MovieListResponse;
import ru.lbarbaris.webservice.dto.links;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public List<Movie> getMoviesList(){
        WebClient webClient = webClientBuilder.build();

        return webClient.get()
                .uri(links.allMovies.getDescription())
                .retrieve()
                .bodyToFlux(Movie.class)
                .collectList()
                .block();
    }

    public List<Movie> getMoviesList(String username){
        WebClient webClient = webClientBuilder.build();
        return webClient.get()
                .uri(links.moviesByUsername.getDescription() + username)
                .retrieve()
                .bodyToFlux(Movie.class)
                .collectList()
                .block();
    }


}
