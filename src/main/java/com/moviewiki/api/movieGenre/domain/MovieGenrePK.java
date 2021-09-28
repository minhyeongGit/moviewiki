package com.moviewiki.api.movieGenre.domain;

import com.moviewiki.api.genre.domain.Genre;
import com.moviewiki.api.movie.domain.Movie;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class MovieGenrePK implements Serializable {

    private Genre genre;
    private Movie movie;

}
