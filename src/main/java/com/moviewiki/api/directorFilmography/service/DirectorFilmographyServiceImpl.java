package com.moviewiki.api.directorFilmography.service;

import com.moviewiki.api.director.domain.Director;
import com.moviewiki.api.directorFilmography.domain.DirectorFilmography;
import com.moviewiki.api.directorFilmography.repository.DirectorFilmographyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorFilmographyServiceImpl implements DirectorFilmographyService{

    @Autowired
    private DirectorFilmographyRepository directorFilmographyRepository;

    @Override
    public List<DirectorFilmography> findDirectorFilmographyListByDirector(Director director){
        return directorFilmographyRepository.findDirectorFilmographyListByDirector(director);
    }
}
