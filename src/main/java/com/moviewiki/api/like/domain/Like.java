package com.moviewiki.api.like.domain;

import com.moviewiki.api.review.domain.Review;
import com.moviewiki.api.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LIKES")
@IdClass(LikePK.class)
public class Like {

    @ManyToOne
    @JoinColumn(name ="REVIEW_ID")
    @Id
    private Review review;

    @ManyToOne
    @JoinColumn(name ="USER_ID")
    @Id
    private User user;

}
