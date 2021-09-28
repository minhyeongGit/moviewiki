package com.moviewiki.api.prefActor.Service;

import com.moviewiki.api.actor.domain.Actor;
import com.moviewiki.api.actorFilmography.domain.ActorFilmography;
import com.moviewiki.api.actorFilmography.repository.ActorFilmographyRepository;
import com.moviewiki.api.movie.domain.Movie;
import com.moviewiki.api.prefActor.domain.PrefActor;
import com.moviewiki.api.prefActor.repository.PrefActorRepository;
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
public class PrefActorServiceImpl implements PrefActorService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private PrefActorRepository prefActorRepository;
    @Autowired
    private ActorFilmographyRepository actorFilmographyRepository;

    private final EntityManager em;
    @Autowired
    public PrefActorServiceImpl(EntityManager em) {
        this.em = em;
    }
//    @Autowired
//    public PrefActorServiceImpl(ReviewRepository reviewRepository, PrefActorRepository prefActorRepository, ActorFilmographyRepository actorFilmographyRepository) {
//        this.reviewRepository = reviewRepository;
//        this.prefActorRepository = prefActorRepository;
//        this.actorFilmographyRepository = actorFilmographyRepository;
//    }

    // 효미 - 배우 선호도 업데이트
    @Override
    public void updatePrefActor(Review review) {
        User user = review.getUser();
        ActorFilmography actorFilmographyByMovie = actorFilmographyRepository.findActorFilmographyByMovie(review.getMovie());
        Actor actor = actorFilmographyByMovie.getActor();

        // 무비배우테이블에 가서 이 배우에 해당하는 무비 리스트 가져오기
        List<Review> reviewListByUserAndActor = new ArrayList<>();
        List<ActorFilmography> actorFilmographyListByActor = actorFilmographyRepository.findActorFilmographyListByActor(actor);
        double sum = 0;
        for (ActorFilmography actorFilmographyByActor : actorFilmographyListByActor) {
            Movie tempMovieByActor = actorFilmographyByActor.getMovie();
            // 각 무비에 대해 리뷰테이블에 가서 이 무비와 이 유저에 해당하는 리뷰 가져오기
            Review reviewByUserAndMovie = reviewRepository.findReviewByUserAndMovie(user, tempMovieByActor);
            sum += reviewByUserAndMovie.getReviewRating();
            reviewListByUserAndActor.add(reviewByUserAndMovie);
        }
        int actorReviewCount = reviewListByUserAndActor.size();
        double actorPoint = sum/actorReviewCount;
        Date actorReviewDate = new Date();
        PrefActor prefActor = new PrefActor(user, actor, actorPoint, actorReviewCount, actorReviewDate); // PrefActor 생성
        prefActorRepository.save(prefActor); //PrefActors 테이블에 저장
    }


    // 민형 - 유저로 선호 배우 리스트
    @Override
    public List<PrefActor> prefActorList(User user) {
        List<PrefActor> prefActorList = prefActorRepository.findByUserOrderByActorPointDesc(user);
        return prefActorList.subList(0,3);
    }

    // 선호 배우 영화 추천
    @Override
    public List<Movie> findAll(String userName){
        String sql = "SELECT * FROM MOVIES\n" +
                "WHERE MOVIE_ID IN(\n" +
                "SELECT MOVIE_ID FROM ACTOR_FILMOGRAPHY\n" +
                "WHERE ACTOR_ID IN\n" +
                "    (SELECT ACTOR_ID from PREF_ACTORS \n" +
                "    where ACTOR_POINT =\n" +
                "        (select max(ACTOR_POINT) from PREF_ACTORS where USER_ID = '"+userName+"')))";
        List<Movie> recActorList = em.createNativeQuery(sql, Movie.class).getResultList();
        return recActorList.subList(0, 12);
    }
}
