package com.moviewiki.api.following.service;

import com.moviewiki.api.following.domain.Following;
import com.moviewiki.api.following.domain.FollowingPK;
import com.moviewiki.api.following.repository.FollowingRepository;
import com.moviewiki.api.user.controller.UserManagementController;
import com.moviewiki.api.user.domain.User;
import com.moviewiki.api.user.service.UserManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class FollowingServiceImpl implements FollowingService {
    private static final Logger log = LoggerFactory.getLogger(UserManagementController.class);

    @Autowired
    FollowingRepository followingRepository;

    @Autowired
    UserManagementService userManagementService;

    @Autowired
    EntityManager em; // 복합키 저장, 삭제를 위해 필요



    // 팔로잉 리스트 출력
    @Override
    public List<Following> followeeList(User follower) {
        List<Following> followeeList = followingRepository.findFolloweeByFollower(follower);
        return followeeList;
    }

    // 팔로워 리스트 출력
    @Override
    public List<Following> followerList(User followee) {
        List<Following> followerList = followingRepository.findFollowerByFollowee(followee);
        return followerList;
    }

    // 팔로워 수
    @Override
    public int countFollower(User followee) {
        return followingRepository.countFollowerByFollowee(followee);
    }

    // 팔로잉 수
    @Override
    public int countFollowee(User follower) {
        return followingRepository.countFolloweeByFollower(follower);
    }


    // 팔로우 상태 확인
    @Override
    public boolean isFollowing(User follower, User followee) {
        return followingRepository.existsByFollowerAndFollowee(follower, followee);
    }


    // 팔로우
    @Override
    @Transactional // 이거 안 붙이면 오류 나더라고요
    public void followUser(User follower, User followee) {
        log.info("follower =========== " + follower);
        log.info("followee =========== " + followee);
        Following following = new Following(); // 객체 생성
        following.setFollower(follower);    // 값 세팅
        following.setFollowee(followee);

        em.persist(following);  // 저장
    }

    //언팔로우
    @Override
    @Transactional
    public void unfollowUser(User follower, User followee) {
        FollowingPK followingPK = new FollowingPK(follower, followee); // 기본키에 세팅
        Following following = em.find(Following.class, followingPK); // 엔티티에 있는지 찾기
        em.remove(following); // 삭제
    }
}