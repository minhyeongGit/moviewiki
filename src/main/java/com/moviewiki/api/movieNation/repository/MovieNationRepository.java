package com.moviewiki.api.movieNation.repository;

import com.moviewiki.api.genre.domain.Genre;
import com.moviewiki.api.movie.domain.Movie;
import com.moviewiki.api.movieGenre.domain.MovieGenre;
import com.moviewiki.api.movieNation.domain.MovieNation;
import com.moviewiki.api.movieNation.domain.MovieNationPK;
import com.moviewiki.api.nation.domain.Nation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieNationRepository extends JpaRepository<MovieNation, MovieNationPK> {

    // 효미 - 국가 선호도 업데이트에 사용
    MovieNation findMovieNationByMovie(Movie movie);

    // 효미 - 국가 선호도 업데이트에 사용
    List<MovieNation> findMovieNationListByNation(Nation nation);

    // 리턴값이 List인거랑 아닌게 있어서 리턴값이 List인 메소드명에 List가 들어가도록 수정했어요 - 효미

}
