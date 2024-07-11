package ru.lbarbaris.webservice.dto.dataService;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDataRequest {

    private long id;

    private String username;

    int cinemaCount;

    @JsonProperty("movies")
    private List<Movie> movies;
}
