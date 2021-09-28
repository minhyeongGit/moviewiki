package com.moviewiki.api.like.domain;

import com.moviewiki.api.review.domain.Review;
import com.moviewiki.api.user.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class LikePK implements Serializable {

    private Review review;
    private User user;

}

