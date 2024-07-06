package ru.lbarbaris.webservice.dto.dataService;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MovieRequest {
    private long id;

    private String imageurl;

    private String name;

    private String description;

    private float rating;

    @JsonProperty("userdata")
    private UserData userdata;
}
