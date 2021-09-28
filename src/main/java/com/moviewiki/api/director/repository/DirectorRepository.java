package com.moviewiki.api.director.repository;

import com.moviewiki.api.actor.domain.Actor;
import com.moviewiki.api.director.domain.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectorRepository extends JpaRepository<Director, String> {
    // 특정 배우 찾기 -> 이름으로
    List<Director> findByDirectorName(String actorName);

    // 감독 상세 페이지 조회
    Director findByDirectorId(Long directorId);
}
