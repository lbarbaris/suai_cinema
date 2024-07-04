package ru.lbarbaris.dataservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lbarbaris.dataservice.entity.Movie;
import ru.lbarbaris.dataservice.entity.MovieResponse;
import ru.lbarbaris.dataservice.entity.UserData;
import ru.lbarbaris.dataservice.repository.MovieRepository;
import ru.lbarbaris.dataservice.repository.UserDataRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserDataRepository userDataRepository;

    @Override
    public Movie save(MovieResponse movieResponse) {
        UserData userData;
        Movie movie;

        //проверяем UserData
        if (movieResponse.getUserDataInfo().getId() == 0L){  // если создаём
            userData = new UserData(0L,
                    movieResponse.getUserDataInfo().getUsername(),
                    movieResponse.getUserDataInfo().getCinemaCount(),
                    new ArrayList<Movie>());
        }
        else{ // если апдейтим
            userData = userDataRepository.getReferenceById(movieResponse.getUserDataInfo().getId());
            userData.setUsername(movieResponse.getUserDataInfo().getUsername());
            userData.setCinemaCount(movieResponse.getUserDataInfo().getCinemaCount());
        }


        //проверяем Movie
        if (movieResponse.getId() == 0L){ // если создаём
            movie = new Movie(
                    0L,
                    movieResponse.getImageurl(),
                    movieResponse.getName(),
                    movieResponse.getDescription(),
                    movieResponse.getRating(),
                    userData);
        }
        else { //если апдейтим
            movie = movieRepository.getReferenceById(movieResponse.getId());
            movie.setImageurl(movieResponse.getImageurl());
            movie.setName(movieResponse.getName());
            movie.setDescription(movieResponse.getDescription());
            movie.setRating(movieResponse.getRating());
            movie.setUserdata(userData);
        }


        List<Movie> movies = userData.getMovies();
        movies.add(movie);
        userData.setMovies(movies);
        return movieRepository.save(movie);
    }


    public List<Movie> getAllByUserdataUsername(String username) {
        return movieRepository.getAllByUserdataUsername(username);
    }


    @Override
    public Optional<Movie> findById(Long aLong) {
        return movieRepository.findById(aLong);
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public void deleteById(Long aLong) {
        movieRepository.deleteById(aLong);
    }
}
