package com.moviewiki.api.movieNation.domain;

import com.moviewiki.api.movie.domain.Movie;
import com.moviewiki.api.nation.domain.Nation;

import java.io.Serializable;

public class MovieNationPK implements Serializable {

    private Nation nation;
    private Movie movie;

}
