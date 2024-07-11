package ru.lbarbaris.telegramservice.dto;

public enum links {

    allMovies("http://localhost:8081/movies"),
    allUsers("http://localhost:8081/userDatas"),
    userDataByUsername("http://localhost:8081/userDatas/search/getUserDataByUsername?username="),
    moviesByUsername("http://localhost:8081/movies/search/getAllByUserdataUsername?username="),
    moviesDb("https://api.themoviedb.org/3/trending/all/day?api_key="),
    poster("https://image.tmdb.org/t/p/w600_and_h900_bestv2");


    private String description;
    links(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
