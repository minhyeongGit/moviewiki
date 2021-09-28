package com.moviewiki.api.prefGenre.service;

import com.moviewiki.api.movie.domain.Movie;
import com.moviewiki.api.prefActor.domain.PrefActor;
import com.moviewiki.api.prefGenre.domain.PrefGenre;
import com.moviewiki.api.review.domain.Review;
import com.moviewiki.api.user.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PrefGenreService {

    // 효미 - 장르 선호도 업데이트
    void updatePrefGenre(Review review);

    // 선호 장르 영화 추천
    public List<Movie> findAll(String userName);

    // 민형 - 유저로 선호 장르 리스트
    List<PrefGenre> prefGenreList(User user);
}
