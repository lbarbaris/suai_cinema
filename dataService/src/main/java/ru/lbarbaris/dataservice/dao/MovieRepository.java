package ru.lbarbaris.dataservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lbarbaris.dataservice.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
