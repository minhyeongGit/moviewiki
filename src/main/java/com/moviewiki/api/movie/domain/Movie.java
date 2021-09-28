package com.moviewiki.api.movie.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "MOVIE_SEQ_GENERATOR",
        sequenceName = "MOVIE_SEQ",
        initialValue = 1,
        allocationSize = 1)
@Table(name="MOVIES")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
                    , generator = "MOVIE_SEQ_GENERATOR")
    @Column(name = "MOVIE_ID")
    private Long movieId;

    @Column(name = "FILM_RATING")
    private String filmRating;
    @Column(name = "MOVIE_NAME")
    private String movieName;
    @Column(name = "MOVIE_OG_NAME")
    private String movieOgName;
    @Column(name = "MOVIE_PROFILE", length=200)
    private String movieProfile;
    @Column(name = "RELEASE_DATE", columnDefinition = "DATE")
    private Date releaseDate;
    private int runningTime;
    @Column(columnDefinition = "LONG")
    private String summary;

    @Column(name = "AVG_RATING")
    private double avgRating;
    @Column(name = "REVIEW_COUNT")
    private int reviewCount;
    @Column(name = "WTS_COUNT")
    private int wtsCount;

    @Builder
    public Movie(String filmRating, String movieName, String movieOgName, String movieProfile, Date releaseDate, int runningTime, String summary
            , double avgRating, int reviewCount, int wtsCount) {
        this.filmRating = filmRating;
        this.movieName = movieName;
        this.movieOgName = movieOgName;
        this.movieProfile = movieProfile;
        this.releaseDate = releaseDate;
        this.runningTime = runningTime;
        this.summary = summary;
        this.avgRating = avgRating;
        this.reviewCount = reviewCount;
        this.wtsCount = wtsCount;
    }
}

