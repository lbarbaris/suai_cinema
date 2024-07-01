package ru.lbarbaris.webservice.dto;

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


}
