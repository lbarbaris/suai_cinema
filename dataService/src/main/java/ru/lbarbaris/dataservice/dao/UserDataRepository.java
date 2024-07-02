package ru.lbarbaris.dataservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.lbarbaris.dataservice.entity.Movie;
import ru.lbarbaris.dataservice.entity.UserData;

import java.util.List;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {

    public UserData getUserDataByUsername(@Param("username") String username);


}
