package ru.lbarbaris.dataservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lbarbaris.dataservice.entity.Movie;
import ru.lbarbaris.dataservice.entity.UserData;
import ru.lbarbaris.dataservice.entity.UserDataResponse;
import ru.lbarbaris.dataservice.repository.MovieRepository;
import ru.lbarbaris.dataservice.repository.UserDataRepository;

import java.util.List;
import java.util.Optional;
@Service
public class UserDataServiceImpl implements UserDataService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserDataRepository userDataRepository;

    @Override
    public UserData save(UserDataResponse userDataResponse) {
        UserData userData;

        //проверяем UserData
        if (userDataResponse.getId() == 0L){  // если создаём
            userData = new UserData(0L,
                    userDataResponse.getUsername(),
                    userDataResponse.getCinemaCount(),
                    userDataResponse.getMovies());

        }
        else{ // если апдейтим
            userData = userDataRepository.getReferenceById(userDataResponse.getId());
            userData.setUsername(userDataResponse.getUsername());
            userData.setCinemaCount(userDataResponse.getCinemaCount());
            userData.setMovies(userDataResponse.getMovies());
        }

        for (int i = 0; i < userData.getMovies().size(); i++){
            userData.getMovies().get(i).setUserdata(userData);
        }

        return userDataRepository.save(userData);
    }

    @Override
    public UserData getAllByUserdataUsername(String username) {
        return userDataRepository.findByUsername(username);
    }


    @Override
    public Optional<UserData> findById(Long aLong) {
        return userDataRepository.findById(aLong);
    }

    @Override
    public List<UserData> findAll() {
        return userDataRepository.findAll();
    }

    @Override
    public void deleteById(Long aLong) {
        userDataRepository.deleteById(aLong);
    }


}
