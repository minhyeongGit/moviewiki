package com.moviewiki.api.user.service;

import com.moviewiki.api.user.domain.User;

import java.util.List;

public interface UserManagementService {
    // 회원 등록
    void createUser(User user);

    // 회원 삭제
    void deleteUser(String userId);

    // 회원 조회
    User getUser(String userId);

    // 회원정보 수정 메소드
    void updateUser(User user);

    // 아이디 찾기 메소드
    User findId(String userName, String userMail);

    // 비밀번호 찾기 메소드
    User findPw(String userId, String userName, String userMail);

    // 회원 전체 조회
    List<User> getAllUser();

}
