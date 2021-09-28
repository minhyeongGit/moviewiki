package com.moviewiki.api.review.service;

import com.moviewiki.api.like.domain.Like;
import com.moviewiki.api.like.repository.LikeRepository;
import com.moviewiki.api.prefActor.Service.PrefActorService;
import com.moviewiki.api.prefDirector.service.PrefDirectorService;
import com.moviewiki.api.prefGenre.service.PrefGenreService;
import com.moviewiki.api.prefNation.Service.PrefNationService;
import com.moviewiki.api.review.domain.Review;
import com.moviewiki.api.review.repository.ReviewRepository;
import com.moviewiki.api.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private PrefGenreService prefGenreService;
    @Autowired
    private PrefNationService prefNationService;
    @Autowired
    private PrefDirectorService prefDirectorService;
    @Autowired
    private PrefActorService prefActorService;


//    public ReviewServiceImpl(ReviewRepository reviewRepository, LikeRepository likeRepository, PrefGenreService prefGenreService, PrefNationService prefNationService, PrefDirectorService prefDirectorService, PrefActorService prefActorService) {
//
//        this.reviewRepository = reviewRepository;
//        this.likeRepository = likeRepository;
//        this.prefGenreService = prefGenreService;
//        this.prefNationService = prefNationService;
//        this.prefDirectorService = prefDirectorService;
//        this.prefActorService = prefActorService;
//
//    }

    // 효미 - 리뷰 등록 & 선호도 업데이트
    @Override
    public void doReview(Review review) {

        reviewRepository.save(review); // 리뷰 등록
        prefGenreService.updatePrefGenre(review); // 장르 선호도 업데이트
        prefNationService.updatePrefNation(review); // 국가 선호도 업데이트
        prefDirectorService.updatePrefDirector(review); // 감독 선호도 업데이트
        prefActorService.updatePrefActor(review); // 배우 테이블 업데이트

    }


    // 민형 - 리뷰 수정 & 선호도 업데이트
    @Transactional
    @Override
    public void modifyReview(Review review) {

        reviewRepository.save(review); // 리뷰 수정
        prefGenreService.updatePrefGenre(review); // 장르 선호도 업데이트
        prefNationService.updatePrefNation(review); // 국가 선호도 업데이트
        prefDirectorService.updatePrefDirector(review); // 감독 선호도 업데이트
        prefActorService.updatePrefActor(review); // 배우 테이블 업데이트

    }

    // 민형 - 리뷰 삭제 & 선호도 업데이트
    @Transactional
    @Override
    public void deleteReview(Long reviewId) {

        reviewRepository.deleteByReviewId(reviewId); // 리뷰 삭제
        Review review = reviewRepository.findByReviewId(reviewId);
        prefGenreService.updatePrefGenre(review); // 장르 선호도 업데이트
        prefNationService.updatePrefNation(review); // 국가 선호도 업데이트
        prefDirectorService.updatePrefDirector(review); // 감독 선호도 업데이트
        prefActorService.updatePrefActor(review); // 배우 테이블 업데이트

    }

    @Override
    public void likeReview (Review review, User user) {
        Like like = new Like(review, user);
        likeRepository.save(like);

    }

    @Override
    public void unlikeReview(Review review, User user) {
        Like like = new Like(review, user);
        likeRepository.delete(like);

    }

    // 민형 - 리뷰(코멘트) 수
    @Override
    public int countReviews(User user) {
        return reviewRepository.countReviewByUser(user);
    }

    // 민형 - 리뷰(평점) 수
    @Override
    public int countStars(User user) {

        int countReview = reviewRepository.countReviewByUser(user);

        List<Review> reviewList = reviewRepository.findByUser(user);
        for(Review review : reviewList) {
            if(review.getReviewComment() == null) {
              countReview -= 1;
            }
        }
        return countReview;
    }

    // 민형 - 사용자 기준으로 리뷰 조회
    @Override
    public List<Review> getReviewListByUser(User user) {
        return reviewRepository.findByUser(user);
    }


    // 민형 - 내가 리뷰한 영화 감상 시간
    @Override
    public int myRunningtime(List<Review> reviewList) {
        int runningTime = 0;
        for(Review review : reviewList) {
            int runningTimeOfMovie = review.getMovie().getRunningTime();
            runningTime += runningTimeOfMovie;
        }
        return runningTime;
    }

    // 민형 - 리뷰 삭제
//    @Transactional
//    @Override
//    public void removeReview(Long reviewId) {
//        reviewRepository.deleteByReviewId(reviewId);
//    }

    // 민형 - 리뷰 조회
    public Review getReview(Long reviewId) {
        return reviewRepository.findByReviewId(reviewId);
    }

}
