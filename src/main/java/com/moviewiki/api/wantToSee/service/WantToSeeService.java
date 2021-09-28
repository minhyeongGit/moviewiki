package com.moviewiki.api.wantToSee.service;

import com.moviewiki.api.movie.domain.Movie;
import com.moviewiki.api.user.domain.User;
import com.moviewiki.api.wantToSee.domain.WantToSee;

import java.util.List;

public interface WantToSeeService {

    /* 관심영화 추가 */
    public void save(User user, Movie movie);

    /* 특정 사용자의 관심영화 조회 -> 사용자 아이디로 기준 */
    public List<WantToSee> findByUser(User user);

    /* 관심영화 삭제 -> 사용자아이디, 영화아이디 */
    public void deleteWantToSee(User user, Movie movie);

    // 관심영화 여부 확인
    boolean isWant(User user, Movie movie);

    // 민형 - 보고싶은 영화 수
    int countWantToSee(User user);
}
