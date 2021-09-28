package com.moviewiki.api.movie.service;

import com.moviewiki.api.movie.domain.Movie;
import com.moviewiki.api.movie.domain.MovieForm;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    // 새로운 영화 저장 및 update
    void save(Movie movie);

    // 특정 영화 조회
    Optional<Movie> findById(Long movieId);

    // 특정 영화 조회
    Movie findByMovieId(Long movieId);

    // 모든 영화
    List<Movie> findAll();

    // 평점 높은 순으로 영화 조회
    List<Movie> findAllOrderByRating();

    // 리뷰 많은 순으로 영화 조회
    List<Movie> findAllOrderByReviewCount();
    // 최신 순으로 영화 조회
    List<Movie> findAllOrderByDate();

    // 입력받는 검색 test
//    public List<Movie> findByMovieNameContaining(String movieName);
    public List<MovieForm> searchMovies(String keyword);
}
