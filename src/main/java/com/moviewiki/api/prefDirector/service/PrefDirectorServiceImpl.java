package com.moviewiki.api.prefDirector.service;

import com.moviewiki.api.director.domain.Director;
import com.moviewiki.api.directorFilmography.domain.DirectorFilmography;
import com.moviewiki.api.directorFilmography.repository.DirectorFilmographyRepository;
import com.moviewiki.api.movie.domain.Movie;
import com.moviewiki.api.prefDirector.domain.PrefDirector;
import com.moviewiki.api.prefDirector.repository.PrefDirectorRepository;
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
public class PrefDirectorServiceImpl implements PrefDirectorService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private PrefDirectorRepository prefDirectorRepository;
    @Autowired
    private DirectorFilmographyRepository directorFilmographyRepository;

    private final EntityManager em;
    @Autowired
    public PrefDirectorServiceImpl(EntityManager em) {
        this.em = em;
    }
//    @Autowired
//    public PrefDirectorServiceImpl(ReviewRepository reviewRepository, PrefDirectorRepository prefDirectorRepository, DirectorFilmographyRepository directorFilmographyRepository) {
//
//        this.reviewRepository = reviewRepository;
//        this.prefDirectorRepository = prefDirectorRepository;
//        this.directorFilmographyRepository = directorFilmographyRepository;
//
//    }

    // 효미 - 감독 선호도 업데이트
    @Override
    public void updatePrefDirector(Review review) {
        User user = review.getUser();
        DirectorFilmography directorFilmographyByMovie = directorFilmographyRepository.findDirectorFilmographyByMovie(review.getMovie());
        Director director = directorFilmographyByMovie.getDirector();

        // 무비감독테이블에 가서 이 감독에 해당하는 무비 리스트 가져오기
        List<Review> reviewListByUserAndDirector = new ArrayList<>();
        List<DirectorFilmography> directorFilmographyListByDirector = directorFilmographyRepository.findDirectorFilmographyListByDirector(director);
        double sum = 0;
        for (DirectorFilmography directorFilmographyByDirector : directorFilmographyListByDirector) {
            Movie tempMovieByDirector = directorFilmographyByDirector.getMovie();
            // 각 무비에 대해 리뷰테이블에 가서 이 무비와 이 유저에 해당하는 리뷰 가져오기
            Review reviewByUserAndMovie = reviewRepository.findReviewByUserAndMovie(user, tempMovieByDirector);
            sum += reviewByUserAndMovie.getReviewRating();
            reviewListByUserAndDirector.add(reviewByUserAndMovie);
        }
        int directorReviewCount = reviewListByUserAndDirector.size();
        double directorPoint = sum/directorReviewCount;
        Date directorReviewDate = new Date();
        PrefDirector prefDirector = new PrefDirector(user, director, directorPoint, directorReviewCount, directorReviewDate); // PrefDirector 생성
        prefDirectorRepository.save(prefDirector); //PrefDirectors 테이블에 저장
    }

    // 민형 - 유저로 선호 감독 리스트(선호 점수순)
    @Override
    public List<PrefDirector> prefDirectorList(User user) {
        List<PrefDirector> prefDirectorList = prefDirectorRepository.findByUserOrderByDirectorPointDesc(user);
        return prefDirectorList.subList(0,3);
    }

    // 선호 감독 영화 추천
    @Override
    public List<Movie> findAll(String userName){
        String sql = "SELECT * FROM MOVIES\n" +
                "WHERE MOVIE_ID IN(\n" +
                "SELECT MOVIE_ID FROM DIRECTOR_FILMOGRAPHY\n" +
                "WHERE DIRECTOR_ID IN\n" +
                "    (SELECT DIRECTOR_ID from PREF_DIRECTORS \n" +
                "    where DIRECTOR_POINT =\n" +
                "        (select max(DIRECTOR_POINT) from PREF_DIRECTORS where USER_ID = '"+userName+"')))";
        List<Movie> recDirectorList = em.createNativeQuery(sql, Movie.class).getResultList();
        return recDirectorList.subList(0, 12);
    }
}
