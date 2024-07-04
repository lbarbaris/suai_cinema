package ru.lbarbaris.dataservice.service;

import ru.lbarbaris.dataservice.entity.Movie;
import ru.lbarbaris.dataservice.entity.MovieResponse;
import ru.lbarbaris.dataservice.entity.UserData;

import java.util.List;

public interface MovieService extends BaseService<Movie, Long> {

    public Movie save(MovieResponse movieResponse);

    public List<Movie> getAllByUserdataUsername(String username);


}
