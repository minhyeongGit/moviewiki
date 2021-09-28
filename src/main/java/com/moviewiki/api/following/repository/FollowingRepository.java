package com.moviewiki.api.following.repository;

import com.moviewiki.api.following.domain.Following;
import com.moviewiki.api.following.domain.FollowingPK;
import com.moviewiki.api.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowingRepository extends JpaRepository<Following, FollowingPK> {

    // 팔로워 리스트
    List<Following> findFollowerByFollowee(User followee);

    // 팔로잉 리스트
    List<Following> findFolloweeByFollower(User follower);

    // 팔로워되어있는지 확인
    boolean existsByFollowerAndFollowee(User follower, User followee);

    // 팔로워 수
    int countFollowerByFollowee(User followee);

    // 팔로잉 수
    int countFolloweeByFollower(User follower);

    // 효미님꺼
    List<Following> findByFollower(User follower);

}