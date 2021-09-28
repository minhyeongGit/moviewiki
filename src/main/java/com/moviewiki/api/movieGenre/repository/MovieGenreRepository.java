package com.moviewiki.api.movieGenre.repository;

import com.moviewiki.api.genre.domain.Genre;
import com.moviewiki.api.movie.domain.Movie;
import com.moviewiki.api.movieGenre.domain.MovieGenre;
import com.moviewiki.api.review.domain.Review;
import com.moviewiki.api.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieGenreRepository extends JpaRepository<MovieGenre, Long> {

    // 효미 - 장르 선호도 업데이트에 사용
    MovieGenre findMovieGenreByMovie(Movie movie);
    // 효미 - 장르 선호도 업데이트에 사용
    List<MovieGenre> findMovieGenreListByGenre(Genre genre);

    List<MovieGenre> findMovieGenreListByMovie(Movie movie);

    // 리턴값이 List인거랑 아닌게 있어서 리턴값이 List인 메소드명에 List가 들어가도록 수정했어요 - 효미


}
