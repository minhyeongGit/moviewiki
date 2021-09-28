package com.moviewiki.api.user.service;

import com.moviewiki.api.user.domain.User;
import com.moviewiki.api.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManagementServiceImpl implements UserManagementService {
    @Autowired
    private UserRepository userRepository;

    // 회원 등록
    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    // 회원 삭제
    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    // 회원 조회
    @Override
    public User getUser(String userId) {
        User user = userRepository.findByUserId(userId);
        return user;
    }

    // 회원정보 수정 메소드
    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    // 아이디 찾기 메소드
    @Override
    public User findId(String userName, String userMail) {
        User user = userRepository.findUserByUserNameAndUserMail(userName, userMail);
        return user;
    }

    // 비밀번호 찾기 메소드
    @Override
    public User findPw(String userId, String userName, String userMail) {
        User user = userRepository.findUserByUserIdAndUserNameAndUserMail(userId, userName, userMail);
        return user;
    }

    // 회원 전체 조회 메소드
    @Override
    public List<User> getAllUser() {
        List<User> userList = userRepository.findAll();
        return userList;
    }
}
