package ru.lbarbaris.dataservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.lbarbaris.dataservice.entity.Movie;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
        public List<Movie> getAllByUserdataUsername(@Param("username") String username);
}
