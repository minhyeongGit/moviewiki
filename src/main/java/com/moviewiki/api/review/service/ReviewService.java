package com.moviewiki.api.review.service;

import com.moviewiki.api.like.domain.Like;
import com.moviewiki.api.review.domain.Review;
import com.moviewiki.api.user.domain.User;

import javax.transaction.Transactional;
import java.util.List;

public interface ReviewService {

    void doReview(Review review); // 효미 - 리뷰 등록
    void modifyReview(Review review); // 민형 - 리뷰 수정
    void deleteReview(Long reviewId); // 민형 - 리뷰 삭제
    void likeReview(Review review, User user);
    void unlikeReview(Review review, User user);


    // 민형 - 리뷰(코멘트) 수
    int countReviews(User user);

    // 민형 - 리뷰(평점) 수
    int countStars(User user);

    // 민형 - 사용자 기준으로 리뷰 조회
    List<Review> getReviewListByUser(User user);

    // 민형 - 내가 리뷰한 영화 감상 시간
    int myRunningtime(List<Review> reviewList);

    // 리뷰 조회
    Review getReview(Long reviewId);

}
