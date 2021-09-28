package com.moviewiki.api.movieGenre.service;

import com.moviewiki.api.genre.domain.Genre;
import com.moviewiki.api.movie.domain.Movie;
import com.moviewiki.api.movie.repository.MovieRepository;
import com.moviewiki.api.movieGenre.domain.MovieGenre;
import com.moviewiki.api.movieGenre.repository.MovieGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class MovieGenreServiceImpl implements MovieGenreService{

    @Autowired
    MovieGenreRepository movieGenreRepository;
    @Autowired
    MovieRepository movieRepository;

    // Genre
    @Override
    public List<MovieGenre> findMovieGenreListByGenre(Genre genre){
        return movieGenreRepository.findMovieGenreListByGenre(genre);
    }

    // Movie
    @Override
    public List<MovieGenre> findMovieGenreListByMovie(Movie movie){
        return movieGenreRepository.findMovieGenreListByMovie(movie);
    }

    // 검색

    private final EntityManager em;

    public MovieGenreServiceImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Movie> findAll(){
        String sql =
// 장르 검색
                "SELECT * FROM MOVIES\n" +
                "WHERE MOVIE_ID IN(\n" +
                "SELECT MOVIE_ID FROM MOVIE_GENRE\n" +
                "WHERE GENRE_ID IN\n" +
                "    (SELECT GENRE_ID FROM GENRES \n" +
                "    WHERE GENRE_NAME LIKE '%드라마%'))";

// 영화 제목 검색
//        "SELECT * FROM MOVIES WHERE MOVIE_NAME LIKE '%크루%'";

        List<Movie> searchList = em.createNativeQuery(sql, Movie.class).getResultList();
        return searchList;
    }

}
