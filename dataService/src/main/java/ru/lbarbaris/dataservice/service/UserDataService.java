package ru.lbarbaris.dataservice.service;
import ru.lbarbaris.dataservice.entity.UserData;
import ru.lbarbaris.dataservice.entity.UserDataResponse;

public interface UserDataService extends BaseService<UserData, Long> {

    public void save(UserDataResponse userDataResponse);

}
