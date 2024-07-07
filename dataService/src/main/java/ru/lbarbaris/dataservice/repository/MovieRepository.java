package ru.lbarbaris.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.lbarbaris.dataservice.entity.Movie;
import ru.lbarbaris.dataservice.entity.UserData;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
        List<Movie> getAllByUserdataUsername(@Param("username") String username);

        void deleteByNameAndUserdata(@Param("name") String name, @Param("userdata") UserData userData);

}
