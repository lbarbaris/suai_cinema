package ru.lbarbaris.telegramservice.service.dataService;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Mono;
import ru.lbarbaris.telegramservice.dto.dataService.Movie;
import ru.lbarbaris.telegramservice.dto.dataService.MovieRequest;
import ru.lbarbaris.telegramservice.dto.dataService.UserData;
import ru.lbarbaris.telegramservice.dto.links;

import java.util.List;

@Log4j2
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
        List<Movie> movieList = null;
        try {
            WebClient webClient = webClientBuilder.build();
            movieList = webClient.get()
                    .uri(links.moviesByUsername.getDescription() + username)
                    .retrieve()
                    .bodyToFlux(Movie.class)
                    .collectList()
                    .block();
        }
        catch (WebClientRequestException e){
            log.error(e);
        }
        return movieList;
    }


    public void saveMovie(Movie movie, UserData userData){
        MovieRequest movieRequest = new MovieRequest(movie.getId(), movie.getImageurl(), movie.getName(), movie.getDescription(), movie.getRating(), userData);
        WebClient webClient = webClientBuilder.build();
                webClient.post()
                .uri(links.allMovies.getDescription())
                .body(Mono.just(movieRequest), MovieRequest.class)
                .retrieve()
                .bodyToMono(MovieRequest.class)
                .block();
    }


    public void deleteMovie(Movie movie, UserData userData){
        MovieRequest movieRequest = new MovieRequest(movie.getId(), movie.getImageurl(), movie.getName(), movie.getDescription(), movie.getRating(), userData);
        WebClient webClient = webClientBuilder.build();
        webClient.post()
                .uri(links.allMovies.getDescription() + "/deleteByMovie")
                .body(Mono.just(movieRequest), MovieRequest.class)
                .retrieve()
                .bodyToMono(MovieRequest.class)
                .block();
    }

    public void deleteMovie(Long id){
        WebClient webClient = webClientBuilder.build();
        webClient.delete()
                .uri(links.allMovies.getDescription() + "/" + id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }



}
