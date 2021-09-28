package com.moviewiki.api.movie.repository;

import com.moviewiki.api.movie.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Movie findByMovieId(Long movieId);

    public List<Movie> findAll();

    // 입력받는 검색 test
    List<Movie> findByMovieNameContaining(String movieName);
//    public List<Movie> getListSearchMovie(String searchName);
}

