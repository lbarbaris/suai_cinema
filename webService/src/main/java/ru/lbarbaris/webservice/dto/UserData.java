package ru.lbarbaris.webservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public final class UserData {

        private long id;

        private String username;

        private int cinemaCount;

        /*private List<Movie> movies;*/

        @JsonProperty("_links")
        private Links links;


        @Getter
        @Setter
        @ToString
        private static class Links{
                @JsonProperty("movies")
                private movie movie;
                @Getter
                @Setter
                @ToString
                private static class movie{
                        private String href;
                }
        }

        public String getMovieLink(){
                return links.getMovie().getHref();
        }
}
