package com.moviewiki.api.season.service;

import com.moviewiki.api.movie.domain.Movie;
import com.moviewiki.api.season.domain.Season;

import java.util.List;

public interface SeasonService {

    // 영화의 계절점수 등록 및 업데이트
    void save(Movie movie,String seasonName, int seasonPoint);
}
