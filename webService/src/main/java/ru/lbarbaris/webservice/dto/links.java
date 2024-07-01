package ru.lbarbaris.webservice.dto;

public enum links {

    allMovies("http://localhost:8081/movies"),
    allUsers("http://localhost:8081/userDatas"),
    userDataByUsername("http://localhost:8081/userDatas/search/getUserDataByUsername?username=");


    private String description;
    links(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
