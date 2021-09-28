package com.moviewiki.api.like.repository;

import com.moviewiki.api.like.domain.Like;
import com.moviewiki.api.like.domain.LikePK;
import com.moviewiki.api.review.domain.Review;
import com.moviewiki.api.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, LikePK> {

//     void save(Like like);
     void delete(Like like);

}
