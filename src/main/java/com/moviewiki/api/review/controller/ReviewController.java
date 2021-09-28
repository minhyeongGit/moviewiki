package com.moviewiki.api.review.controller;

import com.moviewiki.api.review.domain.Review;
import com.moviewiki.api.review.service.ReviewService;
import com.moviewiki.api.user.domain.User;
import com.moviewiki.api.user.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserManagementService userManagementService;

//    public ReviewController(ReviewService reviewService) {
//        this.reviewService = reviewService;
//    }

    // 민형 - 리뷰 삭제
    @RequestMapping("/deleteReview/{reviewId}")
    public String deleteReview(@PathVariable Long reviewId, HttpServletRequest request) {
        String pageId = request.getParameter("userId");
        reviewService.deleteReview(reviewId);

        return "redirect:/member/reviews/" + pageId;
    }

    // 민형 - 리뷰 수정 (마이페이지에서 리뷰 수정)
    @PostMapping("/updateReview/{reviewId}")
    public String updateReview(@PathVariable Long reviewId, Review updateReview, Authentication auth) {
        String loginId = auth.getName();
        // 리뷰 가져오기
        Review review = reviewService.getReview(reviewId);

        // 원래 있던 유저값에 세팅
        review.setReviewComment(updateReview.getReviewComment());
        review.setReviewRating(updateReview.getReviewRating());

        reviewService.modifyReview(review);
        return "redirect:/member/reviews/" + loginId;
    }

    @PostMapping("/do_review")
    public String doReview(Review doReview, HttpServletRequest request) {
        Review review = new Review(doReview.getReviewId(), doReview.getMovie(), doReview.getUser(), doReview.getReviewDate(), doReview.getReviewRating(), doReview.getReviewComment());
        reviewService.doReview(review);
        return "redirect:/see_review/";
    }

    @RequestMapping("/like_review")
    public String likeReview(Long reviewId, String userId, HttpServletRequest request) {
        Review review = reviewService.getReview(reviewId);
        User user = userManagementService.getUser(userId);
        reviewService.likeReview(review, user);
        return "redirect:/see_review/";
    }

    @RequestMapping("/unlike_review")
    public String unlikeReview(Long reviewId, String userId, HttpServletRequest request) {
        Review review = reviewService.getReview(reviewId);
        User user = userManagementService.getUser(userId);
        reviewService.unlikeReview(review, user);
        return "redirect:/see_review/";
    }

}