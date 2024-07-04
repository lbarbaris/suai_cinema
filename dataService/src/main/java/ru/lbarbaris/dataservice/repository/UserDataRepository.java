package ru.lbarbaris.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.lbarbaris.dataservice.entity.UserData;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {

    public UserData getUserDataByUsername(@Param("username") String username);

    public void deleteUserDataByUsername(@Param("username") String username);
}
