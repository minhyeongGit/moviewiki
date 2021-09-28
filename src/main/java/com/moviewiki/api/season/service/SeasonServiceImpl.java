package com.moviewiki.api.season.service;

import com.moviewiki.api.movie.domain.Movie;
import com.moviewiki.api.season.domain.Season;
import com.moviewiki.api.season.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class SeasonServiceImpl implements SeasonService{

    @Autowired
    SeasonRepository seasonRepository;

    @Autowired
    EntityManager em;

    @Override
    @Transactional
    public void save(Movie movie, String seasonName, int seasonPoint) {

        Season season = new Season();
        season.setMovie(movie);
        season.setSeasonName(seasonName);
        season.setSeasonPoint(seasonPoint);

        em.persist(season);
    }
}
