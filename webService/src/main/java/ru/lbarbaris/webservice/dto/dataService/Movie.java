package ru.lbarbaris.webservice.dto.dataService;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@ToString
public final class Movie {


    private long id;

    private String imageurl;

    private String name;

    private String description;

    private float rating;


    public Movie(String imageurl, String name, String description, float rating) {
        this.imageurl = imageurl;
        this.name = name;
        this.description = description;
        this.rating = rating;
    }
}
