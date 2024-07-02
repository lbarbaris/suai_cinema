package ru.lbarbaris.webservice.dataServiceDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.lbarbaris.webservice.dto.Movie;
import ru.lbarbaris.webservice.dto.MovieListResponse;
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
                .bodyToMono(MovieListResponse.class)
                .block()
                .getEmbedded()
                .getMovieList();
    }

    public List<Movie> getMoviesList(String username){
        WebClient webClient = webClientBuilder.build();
        return webClient.get()
                .uri(links.moviesByUsername.getDescription() + username)
                .retrieve()
                .bodyToMono(MovieListResponse.class)
                .block()
                .getEmbedded()
                .getMovieList();
    }


}
