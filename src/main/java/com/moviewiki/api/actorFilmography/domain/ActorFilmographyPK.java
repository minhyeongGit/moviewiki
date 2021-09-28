package com.moviewiki.api.actorFilmography.domain;

import com.moviewiki.api.actor.domain.Actor;
import com.moviewiki.api.movie.domain.Movie;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ActorFilmographyPK implements Serializable {

    private Actor actor;
    private Movie movie;

}
