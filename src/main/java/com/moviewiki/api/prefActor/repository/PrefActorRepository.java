package com.moviewiki.api.prefActor.repository;

import com.moviewiki.api.prefActor.domain.PrefActor;
import com.moviewiki.api.prefActor.domain.PrefActorPK;
import com.moviewiki.api.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrefActorRepository extends JpaRepository<PrefActor, PrefActorPK> {

//     void savePrefActor(PrefActor prefActor);

    // 민형 - 유저로 선호 배우 리스트
    List<PrefActor> findByUserOrderByActorPointDesc(User user);
}