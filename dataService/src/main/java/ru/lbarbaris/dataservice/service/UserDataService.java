package ru.lbarbaris.dataservice.service;
import ru.lbarbaris.dataservice.entity.Movie;
import ru.lbarbaris.dataservice.entity.UserData;
import ru.lbarbaris.dataservice.entity.UserDataResponse;

import java.util.List;

public interface UserDataService extends BaseService<UserData, Long> {

    public UserData save(UserDataResponse userDataResponse);

    public UserData getAllByUserdataUsername(String username);
}
