package com.moviewiki.api.wantToSee.repository;

import com.moviewiki.api.movie.domain.Movie;
import com.moviewiki.api.user.domain.User;
import com.moviewiki.api.wantToSee.domain.WantToSee;
import com.moviewiki.api.wantToSee.domain.WantToSeePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WantToSeeRepository extends JpaRepository<WantToSee, WantToSeePK> {

    /* 특정 사용자의 보고 싶은 영화 조회 -> 사용자 아이디로 기준 */
    List<WantToSee> findByUser(User user);

    // 관심영화 여부 확인
    boolean existsByUserAndMovie(User user, Movie movie);

    // 보고 싶은 영화 수
    int countMovieByUser(User user);
}
