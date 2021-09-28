package com.moviewiki.api.review.repository;

import com.moviewiki.api.movie.domain.Movie;
import com.moviewiki.api.review.domain.Review;
import com.moviewiki.api.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

     // 리뷰 기반, 팔로우 기반 추천에서 쓰이는 메소드
     // 이 유저가 5점 준 Review 리스트
//     @Query("select Review from Review where user = :user and reviewRating = 5")
     List<Review> findHighRatingReviewListByUser(User user);
     // 이 영화에 5점 준 Review 리스트
//     @Query("select Review from Review where movie = :movie and reviewRating = 5")
     List<Review> findHighRatingReviewListByMovie(Movie movie);


     // 효미 - 선호도 업데이트에 쓰는 메소드
     Review findReviewByUserAndMovie(User user, Movie movie);


     // 민형, 효미 - 리뷰 등록 or 수정
//     void save(Review review);

     // 민형, 효미 - 리뷰 삭제
     void deleteByReviewId(Long reviewId);

     // 민형 - 리뷰 수
     int countReviewByUser(User user);

     // 민형 - 사용자 기준으로 리뷰 조회
     List<Review> findByUser(User user);

     // 민형 - 리뷰 조회
     Review findByReviewId(Long reviewId);
}