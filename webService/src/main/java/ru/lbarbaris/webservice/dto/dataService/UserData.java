package ru.lbarbaris.webservice.dto.dataService;

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


}
