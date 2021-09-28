package com.moviewiki.api.review.domain;

import com.moviewiki.api.movie.domain.Movie;
import com.moviewiki.api.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "REVIEWS")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name= "REVIEW_ID")
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name ="MOVIE_ID")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name ="USER_ID")
    private User user;

    @Column(name= "REVIEW_DATE", columnDefinition = "DATE")    // 민형 - 추가
    private Date reviewDate;

    @Column(name= "REVIEW_RATING")   // 민형 - 추가
    private double reviewRating; // 0.5단위

    @Column(name= "REVIEW_COMMENT", columnDefinition = "CLOB") // 민형 추가
    private String reviewComment;

}
