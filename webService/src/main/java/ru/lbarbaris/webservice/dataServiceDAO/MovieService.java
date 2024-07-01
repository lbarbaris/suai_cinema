package ru.lbarbaris.webservice.dataServiceDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.lbarbaris.webservice.dto.Movie;
import ru.lbarbaris.webservice.dto.MovieResponse;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public List<Movie> getMovies(String url){
        WebClient webClient = webClientBuilder.build();
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(MovieResponse.class)
                .block()
                .getEmbedded()
                .getMovieList();
    }
}
