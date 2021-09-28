package com.moviewiki.api.prefGenre.service;

import com.moviewiki.api.genre.domain.Genre;
import com.moviewiki.api.movie.domain.Movie;
import com.moviewiki.api.movieGenre.domain.MovieGenre;
import com.moviewiki.api.movieGenre.repository.MovieGenreRepository;
import com.moviewiki.api.prefGenre.domain.PrefGenre;
import com.moviewiki.api.prefGenre.repository.PrefGenreRepository;
import com.moviewiki.api.prefNation.domain.PrefNation;
import com.moviewiki.api.review.domain.Review;
import com.moviewiki.api.review.repository.ReviewRepository;
import com.moviewiki.api.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PrefGenreServiceImpl implements PrefGenreService {

    @Autowired
    private PrefGenreRepository prefGenreRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MovieGenreRepository movieGenreRepository;

    private final EntityManager em;
    @Autowired
    public PrefGenreServiceImpl(EntityManager em) {
        this.em = em;
    }


//    public PrefGenreServiceImpl(PrefGenreRepository prefGenreRepository, ReviewRepository reviewRepository, MovieGenreRepository movieGenreRepository, EntityManager em) {
//        this.prefGenreRepository = prefGenreRepository;
//        this.reviewRepository = reviewRepository;
//        this.movieGenreRepository = movieGenreRepository;
//        this.em = em;
//    }


    // 효미 - 장르 선호도 업데이트
    @Override
    public void updatePrefGenre(Review review) {
        User user = review.getUser();
        MovieGenre movieGenreByMovie = movieGenreRepository.findMovieGenreByMovie(review.getMovie());
        Genre genre = movieGenreByMovie.getGenre();

        // 무비장르테이블에 가서 이 장르에 해당하는 무비 리스트 가져오기
        List<Review> reviewListByUserAndGenre = new ArrayList<>();
        List<MovieGenre> movieGenreListByGenre = movieGenreRepository.findMovieGenreListByGenre(genre);
        double sum = 0;
        for (MovieGenre movieGenreByGenre : movieGenreListByGenre) {
            Movie tempMovieByGenre = movieGenreByGenre.getMovie();
            // 각 무비에 대해 리뷰테이블에 가서 이 무비와 이 유저에 해당하는 리뷰 가져오기
            Review reviewByUserAndMovie = reviewRepository.findReviewByUserAndMovie(user, tempMovieByGenre);
            sum += reviewByUserAndMovie.getReviewRating();
            reviewListByUserAndGenre.add(reviewByUserAndMovie);
        }
        int genreReviewCount = reviewListByUserAndGenre.size();
        double genrePoint = sum/genreReviewCount;
        Date genreReviewDate = new Date();
        PrefGenre prefGenre = new PrefGenre(user, genre, genrePoint, genreReviewCount, genreReviewDate); // PrefGenre 생성
        prefGenreRepository.save(prefGenre); //PrefGenres 테이블에 저장
    }


    // 선호 장르 영화 추천
    @Override
    public List<Movie> findAll(String userName){
        String sql = "SELECT * FROM MOVIES\n" +
                "WHERE MOVIE_ID IN(\n" +
                "SELECT MOVIE_ID FROM MOVIE_GENRES\n" +
                "WHERE GENRE_ID IN\n" +
                "    (SELECT GENRE_ID from PREF_GENRES \n" +
                "    where GENRE_POINT =\n" +
                "        (select max(GENRE_POINT) from PREF_GENRES where USER_ID = '"+userName+"')))";
        List<Movie> recGenreList = em.createNativeQuery(sql, Movie.class).getResultList();
        return recGenreList.subList(0, 12);
    }


    // 민형 - 유저로 선호 장르 리스트(선호 점수순)
    @Override
    public List<PrefGenre> prefGenreList(User user) {
        List<PrefGenre> prefGenreList = prefGenreRepository.findByUserOrderByGenrePointDesc(user);
        return prefGenreList.subList(0,3);
    }

}
