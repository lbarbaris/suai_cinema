package ru.lbarbaris.webservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@RequiredArgsConstructor
public final class UserData {

        private final long id;

        private final String username;

        private final int cinemaCount;

        private final List<Movie> movies;

        @JsonProperty("_links")
        private Links links;


        @Getter
        @Setter
        private class Links{

                private String movies;
        }
}
