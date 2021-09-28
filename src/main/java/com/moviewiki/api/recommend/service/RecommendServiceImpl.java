package com.moviewiki.api.recommend.service;

import com.moviewiki.api.following.domain.Following;
import com.moviewiki.api.following.repository.FollowingRepository;
import com.moviewiki.api.movie.domain.Movie;
import com.moviewiki.api.review.domain.Review;
import com.moviewiki.api.review.repository.ReviewRepository;
import com.moviewiki.api.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private FollowingRepository followingRepository;

//    public RecommendServiceImpl(ReviewRepository reviewRepository, FollowingRepository followingRepository) {
//        this.reviewRepository = reviewRepository;
//        this.followingRepository = followingRepository;
//    }

    @Override
    public List<Movie> recommendByReview(User user) {
        // 이 회원이 5점 준 리뷰들 List에 담기
        List<Review> highRatingReviewListByUser = reviewRepository.findHighRatingReviewListByUser(user);

        // highRatingReviewListByUser 의 각각의 Review에 대해 Movie 리턴
        List<Review> highRatingReviewListByMovie = new ArrayList<>();
        for (Review highRatingReviewByUser : highRatingReviewListByUser) {
            Movie tempHighRatingMovieByUser = highRatingReviewByUser.getMovie();

            // 그 Movie에 대해 5점 준 리뷰들 List에 담아서
            List<Review> tempHighRatingReviewListByMovie = reviewRepository.findHighRatingReviewListByMovie(tempHighRatingMovieByUser);

            // 합치기
            highRatingReviewListByMovie.addAll(tempHighRatingReviewListByMovie);
        }

        // highRatingReviewListByMovie 의 각각의 Review에 대해 User 리턴
        List<Review> HighRatingReviewListByUser = new ArrayList<>();
        for (Review highRatingReviewByMovie : highRatingReviewListByMovie) {
            User tempHighRatingUserByMovie = highRatingReviewByMovie.getUser();

            // 그 User가 5점 준 리뷰들 List에 담아서
            List<Review> tempHighRatingReviewListByUser = reviewRepository.findHighRatingReviewListByUser(tempHighRatingUserByMovie);

            // 합치기
            HighRatingReviewListByUser.addAll(tempHighRatingReviewListByUser);
        }

        // 각각 Movie 반환해서 리스트에 넣기
        List<Movie> highRatingMovieListByUser = new ArrayList<>();
        for (Review HighRatingReviewByUser : HighRatingReviewListByUser) {
            Movie tempHighRatingMovieByUser = HighRatingReviewByUser.getMovie();
            highRatingMovieListByUser.add(tempHighRatingMovieByUser);
        }

        // 20개 랜덤 추출해서 최종 추천목록 리턴
        List<Movie> movieListByReviewRecommending = new ArrayList<>();
        Collections.shuffle(highRatingMovieListByUser);
        for (int i=0; i<20; i++) {
            movieListByReviewRecommending.add(highRatingMovieListByUser.get(i));
        }
        return movieListByReviewRecommending;
    }

    @Override
    public List<Movie> recommendByFollowing(User user) {

        // 이 유저의 Following List에 담기
       List<Following> followingListByFollower = followingRepository.findByFollower(user);
       // 각각의 Following에 대해 Followee 리턴해서 합치기
       List<User> followeeList = new ArrayList<>();
       for (Following followingByFollower : followingListByFollower) {
           User followee = followingByFollower.getFollowee();
           followeeList.add(followee);
       }

        // followeeList의 각 유저가 5점 준 Review들 리스트에 담기
        List<Review> highRatingReviewListByUser = new ArrayList<>();
        for (User followee : followeeList) {
            List<Review> tempHighRatingReviewListByUser = reviewRepository.findHighRatingReviewListByUser(followee);
            highRatingReviewListByUser.addAll(tempHighRatingReviewListByUser);
        }

        // 각각 Movie 반환해서 리스트에 넣기
        List<Movie> highRatingMovieListByUser = new ArrayList<>();
        for (Review highRatingReviewByUser : highRatingReviewListByUser) {
            Movie tempHighRatingMovieByUser = highRatingReviewByUser.getMovie();
            highRatingMovieListByUser.add(tempHighRatingMovieByUser);
        }

        // 20개 랜덤 추출해서 최종 추천목록 리턴
        List<Movie> movieListByFollowingRecommending = new ArrayList<>();
        Collections.shuffle(highRatingMovieListByUser);
        for (int i=0; i<20; i++) {
            movieListByFollowingRecommending.add(highRatingMovieListByUser.get(i));
        }
        return movieListByFollowingRecommending;

    }

}