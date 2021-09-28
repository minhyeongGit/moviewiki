package com.moviewiki.api.user.controller;

import com.moviewiki.api.following.service.FollowingService;
import com.moviewiki.api.prefActor.Service.PrefActorService;
import com.moviewiki.api.prefActor.domain.PrefActor;
import com.moviewiki.api.prefDirector.domain.PrefDirector;
import com.moviewiki.api.prefDirector.service.PrefDirectorService;
import com.moviewiki.api.prefGenre.domain.PrefGenre;
import com.moviewiki.api.prefGenre.service.PrefGenreService;
import com.moviewiki.api.prefNation.Service.PrefNationService;
import com.moviewiki.api.prefNation.domain.PrefNation;
import com.moviewiki.api.review.domain.Review;
import com.moviewiki.api.review.service.ReviewService;
import com.moviewiki.api.user.domain.User;
import com.moviewiki.api.user.service.UserManagementService;
import com.moviewiki.api.wantToSee.domain.WantToSee;
import com.moviewiki.api.wantToSee.service.WantToSeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MypageProcessController {

    @Autowired
    FollowingService followingService;

    @Autowired
    UserManagementService userManagementService;

    @Autowired
    WantToSeeService wantToSeeService;

    @Autowired
    ReviewService reviewService;

    @Autowired
    PrefActorService prefActorService;

    @Autowired
    PrefDirectorService prefDirectorService;

    @Autowired
    PrefGenreService prefGenreService;

    @Autowired
    PrefNationService prefNationService;

    // 마이페이지 form call
    @GetMapping("/member/mypage/{userId}")
    public String mypageMain(@PathVariable String userId, Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User currentUser) {
        User loginUser = userManagementService.getUser(currentUser.getUsername());
        User pageUser = userManagementService.getUser(userId);

        model.addAttribute("countStar", reviewService.countStars(pageUser));
        model.addAttribute("countReview", reviewService.countReviews(pageUser));
        model.addAttribute("countWantToSee", wantToSeeService.countWantToSee(pageUser));
        model.addAttribute("countFollower", followingService.countFollower(pageUser));
        model.addAttribute("countFollowee", followingService.countFollowee(pageUser));
        model.addAttribute("isFollowing", followingService.isFollowing(loginUser, pageUser));
        model.addAttribute("currentUserId", currentUser.getUsername());
        model.addAttribute("user", userManagementService.getUser(userId));
        return "member/mypage";
    }
    private static final Logger log = LoggerFactory.getLogger(UserManagementController.class);

    // 취향분석 페이지 form call
    @RequestMapping("/member/pref/{userId}")
    public String prefPage(@PathVariable String userId, Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User currentUser) {
        User pageUser = userManagementService.getUser(userId);
        User loginUser = userManagementService.getUser(currentUser.getUsername());
        List<Review> reviewList = reviewService.getReviewListByUser(pageUser);

        model.addAttribute("prefActorList", prefActorService.prefActorList(pageUser));
        model.addAttribute("prefDirectorList", prefDirectorService.prefDirectorList(pageUser));
        model.addAttribute("prefGenreList", prefGenreService.prefGenreList(pageUser));
        model.addAttribute("prefNationList", prefNationService.prefNationList(pageUser));

        model.addAttribute("isFollowing", followingService.isFollowing(loginUser, pageUser));
        model.addAttribute("countReview", reviewService.countReviews(pageUser));
        model.addAttribute("user", pageUser);
        model.addAttribute("myRunningTime", reviewService.myRunningtime(reviewList));
        model.addAttribute("countReview", reviewService.countReviews(pageUser));
        model.addAttribute("currentUserId", currentUser.getUsername());
        return "/member/pref";
    }

    // 시청한 영화 페이지 form call
    @RequestMapping("/member/reviews/{userId}")
    public String reviewsPage(@PathVariable String userId, Model model, Authentication auth) {
        User pageUser = userManagementService.getUser(userId);
        User loginUser = userManagementService.getUser(auth.getName());

        model.addAttribute("isFollowing", followingService.isFollowing(loginUser, pageUser));
        model.addAttribute("countReview", reviewService.countReviews(pageUser));
        model.addAttribute("user", pageUser);
        model.addAttribute("currentUserId", auth.getName());
        model.addAttribute("reviewList", reviewService.getReviewListByUser(pageUser));
        return "/member/reviews";
    }

    // 관심 영화 페이지 form call
    @RequestMapping("/member/want_to_see/{userId}")
    public String wantToSeePage(@PathVariable String userId, Model model, Authentication auth) {
        User loginUser = userManagementService.getUser(auth.getName());
        User pageUser = userManagementService.getUser(userId);
        List<WantToSee> wantToSeeList = wantToSeeService.findByUser(pageUser);

        model.addAttribute("isFollowing", followingService.isFollowing(loginUser, pageUser));
        model.addAttribute("countReview", reviewService.countReviews(pageUser));
        model.addAttribute("countWantToSee", wantToSeeService.countWantToSee(pageUser));
        model.addAttribute("user", pageUser);
        model.addAttribute("currentUserId", loginUser.getUserId());
        model.addAttribute("wantToSeeList", wantToSeeList);

        return "/member/want_to_see";
    }

    // 팔로잉 리스트 출력, form call
    @RequestMapping("/member/followeeList/{userId}")
    public String followeePage(@PathVariable String userId, Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User currentUser) {
        User loginUser = userManagementService.getUser(currentUser.getUsername());
        User pageUser = userManagementService.getUser(userId);

        model.addAttribute("countFollowee", followingService.countFollowee(pageUser));
        model.addAttribute("isFollowing", followingService.isFollowing(loginUser, pageUser));
        model.addAttribute("countReview", reviewService.countReviews(pageUser));
        model.addAttribute("user", pageUser);
        model.addAttribute("currentUserId", currentUser.getUsername());
        model.addAttribute("followeeList", followingService.followeeList(pageUser));
        return "/member/followee";
    }

    // 팔로워 리스트 출력, form call
    @RequestMapping("/member/followerList/{userId}")
    public String followerPage(@PathVariable String userId, Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User currentUser) {
        User pageUser = userManagementService.getUser(userId);
        User loginUser = userManagementService.getUser(currentUser.getUsername());

        model.addAttribute("countFollower", followingService.countFollower(pageUser));
        model.addAttribute("isFollowing", followingService.isFollowing(loginUser, pageUser));
        model.addAttribute("countReview", reviewService.countReviews(pageUser));
        model.addAttribute("user", pageUser);
        model.addAttribute("currentUserId", currentUser.getUsername());
        model.addAttribute("followerList", followingService.followerList(pageUser));
        return "/member/follower";
    }
}
