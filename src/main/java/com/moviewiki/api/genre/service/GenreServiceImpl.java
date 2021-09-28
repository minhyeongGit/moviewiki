package com.moviewiki.api.genre.service;

import com.moviewiki.api.genre.domain.Genre;
import com.moviewiki.api.genre.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService{

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public Optional<Genre> findById(String genreId) {
        return genreRepository.findById(genreId);
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }
}
