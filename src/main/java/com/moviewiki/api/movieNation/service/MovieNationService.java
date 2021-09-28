package com.moviewiki.api.movieNation.service;

import com.moviewiki.api.movieNation.domain.MovieNation;
import com.moviewiki.api.nation.domain.Nation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieNationService {

    public List<MovieNation> findMovieNationListByNation(Nation nation);
}
