package com.moviewiki.api.prefNation.Service;


import com.moviewiki.api.prefNation.domain.PrefNation;
import com.moviewiki.api.movie.domain.Movie;
import com.moviewiki.api.review.domain.Review;
import com.moviewiki.api.user.domain.User;

import java.util.List;


public interface PrefNationService {

    // 효미 - 국가 선호도 업데이트
    void updatePrefNation(Review review);


    // 민형 - 유저로 선호 국가 리스트
    List<PrefNation> prefNationList(User user);

    // 선호 국가 영화 추천
    public List<Movie> findAll(String userName);

}
