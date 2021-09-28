package com.moviewiki.api.prefActor.Service;

import com.moviewiki.api.movie.domain.Movie;
import com.moviewiki.api.prefActor.domain.PrefActor;
import com.moviewiki.api.review.domain.Review;
import com.moviewiki.api.user.domain.User;

import java.util.List;

public interface PrefActorService {

    // 효미 - 배우 선호도 업데이트
    void updatePrefActor(Review review);


    // 민형 - 유저로 선호 배우 리스트
    List<PrefActor> prefActorList(User user);

    // 유미 - 선호 배우 영화 추천
    public List<Movie> findAll(String userName);
}
