package com.moviewiki.api.movieGenre.service;

import com.moviewiki.api.genre.domain.Genre;
import com.moviewiki.api.movie.domain.Movie;
import com.moviewiki.api.movieGenre.domain.MovieGenre;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieGenreService {

    public List<MovieGenre> findMovieGenreListByGenre(Genre genre);
    public List<MovieGenre> findMovieGenreListByMovie(Movie movie);

    public List<Movie> findAll();

}
