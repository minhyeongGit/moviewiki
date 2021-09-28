package com.moviewiki.api.directorFilmography.service;

import com.moviewiki.api.director.domain.Director;
import com.moviewiki.api.directorFilmography.domain.DirectorFilmography;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DirectorFilmographyService {

    public List<DirectorFilmography> findDirectorFilmographyListByDirector(Director director);
}
