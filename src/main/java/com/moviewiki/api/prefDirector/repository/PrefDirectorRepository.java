package com.moviewiki.api.prefDirector.repository;

import com.moviewiki.api.prefActor.domain.PrefActor;
import com.moviewiki.api.prefDirector.domain.PrefDirector;
import com.moviewiki.api.prefDirector.domain.PrefDirectorPK;
import com.moviewiki.api.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrefDirectorRepository extends JpaRepository<PrefDirector, PrefDirectorPK> {

//     void savePrefDirector(PrefDirector prefDirector);

    // 민형 - 유저로 선호 감독 리스트
    List<PrefDirector> findByUserOrderByDirectorPointDesc(User user);
}