package ru.lbarbaris.dataservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import ru.lbarbaris.dataservice.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
public class MovieResponse {



    private long id;

    private String imageurl;

    private String name;

    private String description;

    private float rating;

    @JsonProperty("userdata")
    private UserDataInfo userDataInfo;

    @Getter
    @Setter
    public static class UserDataInfo{

        private long id;

        private String username;

        private int cinemaCount;
    }







}
