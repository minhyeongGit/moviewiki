package com.moviewiki.api.prefNation.repository;

import com.moviewiki.api.prefActor.domain.PrefActor;
import com.moviewiki.api.prefNation.domain.PrefNation;
import com.moviewiki.api.prefNation.domain.PrefNationPK;
import com.moviewiki.api.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrefNationRepository extends JpaRepository<PrefNation, PrefNationPK> {

//     void savePrefNation(PrefNation prefNation);

    // 민형 - 유저로 선호 국가 리스트
    List<PrefNation> findByUserOrderByNationPointDesc(User user);
}