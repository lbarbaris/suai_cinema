package ru.lbarbaris.webservice.dto.site;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class SiteResponse {

    @JsonProperty("results")
    private List<SiteMovie> list;

//Особенность themoviedb.org - фильмы имеют title, сериалы name

    @ToString
    @Getter
    @Setter
    public static class SiteMovie{
        private String title;
        private String name;

        private String poster_path;

        private String overview;

        private float vote_average;
    }




}

