package com.moviewiki.api.movieNation.service;

import com.moviewiki.api.movieNation.domain.MovieNation;
import com.moviewiki.api.movieNation.repository.MovieNationRepository;
import com.moviewiki.api.nation.domain.Nation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieNationServiceImpl implements MovieNationService{

    @Autowired
    private MovieNationRepository movieNationRepository;

    @Override
    public List<MovieNation> findMovieNationListByNation(Nation nation){
        return movieNationRepository.findMovieNationListByNation(nation);
    }
}
