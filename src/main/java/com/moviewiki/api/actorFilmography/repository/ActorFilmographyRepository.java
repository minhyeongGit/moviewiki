package com.moviewiki.api.actorFilmography.repository;

import com.moviewiki.api.actor.domain.Actor;
import com.moviewiki.api.actorFilmography.domain.ActorFilmography;
import com.moviewiki.api.actorFilmography.domain.ActorFilmographyPK;
import com.moviewiki.api.director.domain.Director;
import com.moviewiki.api.directorFilmography.domain.DirectorFilmography;
import com.moviewiki.api.movie.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorFilmographyRepository extends JpaRepository<ActorFilmography, ActorFilmographyPK> {

    // 효미 - 감독 선호도 업데이트에 사용
    ActorFilmography findActorFilmographyByMovie(Movie movie);

    // 효미 - 감독 선호도 업데이트에 사용
    List<ActorFilmography> findActorFilmographyListByActor(Actor actor);

    // 리턴값이 List인거랑 아닌게 있어서 리턴값이 List인 메소드명에 List가 들어가도록 수정했어요 - 효미

}