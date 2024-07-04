package ru.lbarbaris.dataservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class UserDataResponse {

    private long id;

    private String username;

    int cinemaCount;

    @JsonProperty("movies")
    private List<Movie> movies;

}
