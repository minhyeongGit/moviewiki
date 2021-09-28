package com.moviewiki.api.following.controller;

import com.moviewiki.api.following.service.FollowingService;
import com.moviewiki.api.user.controller.UserManagementController;
import com.moviewiki.api.user.domain.User;
import com.moviewiki.api.user.service.UserManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FollowingController {
    private static final Logger log = LoggerFactory.getLogger(UserManagementController.class);

    @Autowired
    UserManagementService userManagementService;

    @Autowired
    FollowingService followingService;

    // 팔로우 기능
    @PostMapping("/follow")
    public String followUser(HttpServletRequest request) {
        String followerId = request.getParameter("followerId");
        String followeeId = request.getParameter("followeeId");

        User follower = userManagementService.getUser(followerId); // 아이디값으로 객체 찾기
        User followee = userManagementService.getUser(followeeId); // getUser = findById

        followingService.followUser(follower, followee);

        return "redirect:/member/mypage/" + followeeId;
    }


    // 언팔로우 기능
    @RequestMapping("/unfollow")
    public String unfollowUser(HttpServletRequest request) {
        String followerId = request.getParameter("followerId");
        String followeeId = request.getParameter("followeeId");

        User follower = userManagementService.getUser(followerId);
        User followee = userManagementService.getUser(followeeId);

        followingService.unfollowUser(follower, followee);

        return "redirect:/member/mypage/" + followeeId;
    }

}
