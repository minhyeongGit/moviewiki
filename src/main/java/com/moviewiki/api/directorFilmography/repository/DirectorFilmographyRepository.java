package com.moviewiki.api.directorFilmography.repository;

import com.moviewiki.api.director.domain.Director;
import com.moviewiki.api.directorFilmography.domain.DirectorFilmography;
import com.moviewiki.api.directorFilmography.domain.DirectorFilmographyPK;
import com.moviewiki.api.genre.domain.Genre;
import com.moviewiki.api.movie.domain.Movie;
import com.moviewiki.api.movieGenre.domain.MovieGenre;
import com.moviewiki.api.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectorFilmographyRepository extends JpaRepository<DirectorFilmography, DirectorFilmographyPK> {

    // 효미 - 감독 선호도 업데이트에 사용
    DirectorFilmography findDirectorFilmographyByMovie(Movie movie);

    // 효미 - 감독 선호도 업데이트에 사용
    List<DirectorFilmography> findDirectorFilmographyListByDirector(Director director);

    // 리턴값이 List인거랑 아닌게 있어서 리턴값이 List인 메소드명에 List가 들어가도록 수정했어요 - 효미

}
