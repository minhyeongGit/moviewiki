package com.moviewiki.api.recommend.service;


import com.moviewiki.api.movie.domain.Movie;
import com.moviewiki.api.user.domain.User;

import java.util.List;

public interface RecommendService {

    List<Movie> recommendByReview(User user);
    List<Movie> recommendByFollowing(User user);

}
