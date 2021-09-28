package com.moviewiki.api.season.repository;

import com.moviewiki.api.season.domain.Season;
import com.moviewiki.api.season.domain.SeasonPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonRepository extends JpaRepository<Season, SeasonPK> {

    // 특정계절의 점수에 따른 정렬
    List<Season> findBySeasonNameOrderBySeasonPointDesc(String seasonName);
}
