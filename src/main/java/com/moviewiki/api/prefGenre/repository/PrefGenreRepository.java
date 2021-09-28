package com.moviewiki.api.prefGenre.repository;

import com.moviewiki.api.genre.domain.Genre;
import com.moviewiki.api.prefActor.domain.PrefActor;
import com.moviewiki.api.prefGenre.domain.PrefGenre;
import com.moviewiki.api.prefGenre.domain.PrefGenrePK;
import com.moviewiki.api.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrefGenreRepository extends JpaRepository<PrefGenre, PrefGenrePK> {


//     void savePrefGenre(PrefGenre prefGenre);
//     void savePrefGenre(PrefGenre prefGenre);

    // 민형 - 유저로 선호 장르 리스트
    List<PrefGenre> findByUserOrderByGenrePointDesc(User user);
}