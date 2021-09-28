package com.moviewiki.api.genre.service;

import com.moviewiki.api.genre.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    Optional<Genre> findById(String genreId);

    List<Genre> findAll();
}
