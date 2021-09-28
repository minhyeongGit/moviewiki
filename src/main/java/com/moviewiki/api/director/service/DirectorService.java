package com.moviewiki.api.director.service;

import com.moviewiki.api.actor.domain.Actor;
import com.moviewiki.api.director.domain.Director;

import java.util.List;
import java.util.Optional;

public interface DirectorService {

    // 새로운 감독 등록
    void save(Director director);

    // 특정 감독 조회 -> 감독코드로
    Optional<Director> findById(String directorId);

    // 특정 감독 조회 -> 이름으로
    List<Director> findByDirectorName(String directorName);

    // 모든 감독 조회
    List<Director> findAll();

    // 감독 상세 페이지 조회
    Director findByDirectorId(Long directorId);
}
