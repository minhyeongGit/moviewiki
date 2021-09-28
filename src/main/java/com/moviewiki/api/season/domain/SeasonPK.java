package com.moviewiki.api.season.domain;

import com.moviewiki.api.movie.domain.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeasonPK implements Serializable {

    private Movie movie;
    private String seasonName;
}
