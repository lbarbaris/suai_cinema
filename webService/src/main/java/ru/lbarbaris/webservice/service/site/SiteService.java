package ru.lbarbaris.webservice.service.site;
import ru.lbarbaris.webservice.dto.site.SiteResponse.SiteMovie;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.lbarbaris.webservice.dto.dataService.Movie;
import ru.lbarbaris.webservice.dto.dataService.MovieListResponse;
import ru.lbarbaris.webservice.dto.links;
import ru.lbarbaris.webservice.dto.site.SiteResponse;

import java.util.ArrayList;
import java.util.List;

import static ru.lbarbaris.webservice.dto.links.poster;

@Service
public class SiteService {

    @Value("${movieAPI.key}")
    private String key;

    @Autowired
    private WebClient.Builder webClientBuilder;



    public List<Movie> getMoviesList(){
        WebClient webClient = webClientBuilder.build();
        SiteResponse siteResponse = webClient.get()
                .uri(links.moviesDb.getDescription() + key)
                .retrieve()
                .bodyToMono(SiteResponse.class)
                .block();

        List<Movie> result = new ArrayList<>();
        for (int i = 0; i < siteResponse.getList().size(); i++){
            SiteMovie siteMovie = siteResponse.getList().get(i);
            //Особенность themoviedb.org - фильмы имеют title, сериалы name
            if (siteResponse.getList().get(i).getTitle() == null){
                result.add(new Movie(
                        poster.getDescription() + siteMovie.getPoster_path(),
                        siteMovie.getName(),
                        siteMovie.getOverview(),
                        siteMovie.getVote_average()
                ));
            }
            else
                result.add(new Movie(
                        poster.getDescription() + siteMovie.getPoster_path(),
                        siteMovie.getTitle(),
                        siteMovie.getOverview(),
                        siteMovie.getVote_average()
                ));

        }

        return result;
    }


}
