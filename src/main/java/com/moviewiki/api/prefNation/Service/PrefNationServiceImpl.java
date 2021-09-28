package com.moviewiki.api.prefNation.Service;

import com.moviewiki.api.nation.domain.Nation;
import com.moviewiki.api.movie.domain.Movie;
import com.moviewiki.api.movieNation.domain.MovieNation;
import com.moviewiki.api.movieNation.repository.MovieNationRepository;
import com.moviewiki.api.nation.domain.Nation;
import com.moviewiki.api.prefNation.domain.PrefNation;
import com.moviewiki.api.prefNation.domain.PrefNation;
import com.moviewiki.api.prefNation.repository.PrefNationRepository;
import com.moviewiki.api.review.domain.Review;
import com.moviewiki.api.review.repository.ReviewRepository;
import com.moviewiki.api.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PrefNationServiceImpl implements PrefNationService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private PrefNationRepository prefNationRepository;
    @Autowired
    private MovieNationRepository movieNationRepository;

    private final EntityManager em;
    @Autowired
    public PrefNationServiceImpl(EntityManager em) {
        this.em = em;
    }

//    @Autowired
//    public PrefNationServiceImpl(ReviewRepository reviewRepository, PrefNationRepository prefNationRepository, MovieNationRepository movieNationRepository,EntityManager em) {
//        this.reviewRepository = reviewRepository;
//        this.prefNationRepository = prefNationRepository;
//        this.movieNationRepository = movieNationRepository;
//        this.em = em;
//    }

    // 효미 - 국가 선호도 업데이트
    @Override
    public void updatePrefNation(Review review) {
        User user = review.getUser();
        MovieNation movieNationByMovie = movieNationRepository.findMovieNationByMovie(review.getMovie());
        Nation nation = movieNationByMovie.getNation();

        // 무비국가 테이블에 가서 이 국가에 해당하는 무비 리스트 가져오기
        List<Review> reviewListByUserAndNation = new ArrayList<>();
        List<MovieNation> movieNationListByNation = movieNationRepository.findMovieNationListByNation(nation);
        double sum = 0;
        for (MovieNation movieNationByNation : movieNationListByNation) {
            Movie tempMovieByNation = movieNationByNation.getMovie();
            // 각 무비에 대해 리뷰테이블에 가서 이 무비와 이 유저에 해당하는 리뷰 가져오기
            Review reviewByUserAndMovie = reviewRepository.findReviewByUserAndMovie(user, tempMovieByNation);
            sum += reviewByUserAndMovie.getReviewRating();
            reviewListByUserAndNation.add(reviewByUserAndMovie);
        }
        int nationReviewCount = reviewListByUserAndNation.size();
        double nationPoint = sum/nationReviewCount;
        Date nationReviewDate = new Date();
        PrefNation prefNation = new PrefNation(user, nation, nationPoint, nationReviewCount, nationReviewDate); // PrefNation 생성
        prefNationRepository.save(prefNation); //PrefNations 테이블에 저장
    }


    // 민형 - 유저로 선호 국가 리스트(선호 점수순)
    @Override
    public List<PrefNation> prefNationList(User user) {

        List<PrefNation> prefNationList = prefNationRepository.findByUserOrderByNationPointDesc(user);
        return prefNationList.subList(0,3);
    }


    // 선호 국가 영화 추천
    @Override
    public List<Movie> findAll(String userName) {
        String sql = "SELECT * FROM MOVIES\n" +
                "WHERE MOVIE_ID IN(\n" +
                "                SELECT MOVIE_ID FROM MOVIE_NATIONS\n" +
                "                WHERE NATION_ID IN\n" +
                "                (SELECT NATION_ID from PREF_NATIONS where NATION_POINT =\n" +
                "                (select max(NATION_POINT) from PREF_NATIONS where USER_ID = '"+userName+"')))";
        List<Movie> recNationList = em.createNativeQuery(sql, Movie.class).getResultList();
        return recNationList.subList(0, 12);

    }
}
