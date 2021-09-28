package com.moviewiki.api.following.service;

import com.moviewiki.api.following.domain.Following;
import com.moviewiki.api.user.domain.User;

import javax.persistence.EntityManager;
import java.util.List;

public interface FollowingService {
    // 팔로이 리스트 출력
    List<Following> followeeList(User follower);

    // 팔로워 리스트 출력
    List<Following> followerList(User followee);

    // 팔로우 상태 확인
    boolean isFollowing(User follower, User followee);

    // 팔로워 수
    int countFollower(User followee);

    // 팔로잉 수
    int countFollowee(User follower);

    // 팔로우
    void followUser(User follower, User followee);

    // 언팔로우
    void unfollowUser(User follower, User followee);
}