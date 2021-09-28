package com.moviewiki.api.directorFilmography.domain;

import com.moviewiki.api.director.domain.Director;
import com.moviewiki.api.movie.domain.Movie;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class DirectorFilmographyPK implements Serializable {

    private Director director;
    private Movie movie;

}
