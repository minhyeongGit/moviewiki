package com.moviewiki.api.user.repository;

import com.moviewiki.api.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, String> {

    // 회원 조회
    User findByUserId(String userId);

    // 아이디 찾기 메소드
    User findUserByUserNameAndUserMail(String userName, String userMail);

    // 비밀번호 찾기 메소드
    User findUserByUserIdAndUserNameAndUserMail(String userId, String userName, String userMail);
}
