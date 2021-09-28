package com.moviewiki.api.wantToSee.service;

import com.moviewiki.api.movie.domain.Movie;
import com.moviewiki.api.user.domain.User;
import com.moviewiki.api.wantToSee.domain.WantToSee;
import com.moviewiki.api.wantToSee.domain.WantToSeePK;
import com.moviewiki.api.wantToSee.repository.WantToSeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class WantToSeeServiceImpl implements WantToSeeService{

    @Autowired
    private WantToSeeRepository wantToSeeRepository;

    @Autowired
    EntityManager em;

    /* 보고픈 영화 추가 */
    @Override
    @Transactional
    public void save(User user, Movie movie) {
        WantToSee wantToSee = new WantToSee();
        wantToSee.setUser(user);
        wantToSee.setMovie(movie);

        em.persist(wantToSee);     // 복합키 저장
    }

    /* 보고픈 영화 조회 -> 사용자 아이디 기준으로 */
    @Override
    public List<WantToSee> findByUser(User user) {
        return wantToSeeRepository.findByUser(user);
    }

    /* 보고픈 영화 삭제 */
    @Override
    @Transactional
    public void deleteWantToSee(User user, Movie movie) {
        WantToSeePK wantToSeePK = new WantToSeePK(movie, user);
        WantToSee wantToSee = em.find(WantToSee.class, wantToSeePK);
        em.remove(wantToSee);   // 삭제
    }

    // 관심영화 여부 확인
    @Override
    public boolean isWant(User user, Movie movie) {
        return wantToSeeRepository.existsByUserAndMovie(user, movie);
    }

    // 보고싶은 영화 수
    @Override
    public int countWantToSee(User user) {
        return wantToSeeRepository.countMovieByUser(user);
    }
}
