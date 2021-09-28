package com.moviewiki.api.recommend.controller;

import com.moviewiki.api.recommend.service.RecommendService;
import com.moviewiki.api.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RecommendController {

    @Autowired
    private RecommendService recommendService;

//    public RecommendController(RecommendService recommendService) {
//        this.recommendService = recommendService;
//    }

    @RequestMapping("/recommend_by_review")
    public String recommendByReview(User user, HttpServletRequest request) {
        recommendService.recommendByReview(user);
        return "redirect:/see_review/";
    }

    @RequestMapping("/recommend_by_following")
    public String recommendByFollwing(User user, HttpServletRequest request) {
        recommendService.recommendByFollowing(user);
        return "redirect:/see_review/";
    }

}
