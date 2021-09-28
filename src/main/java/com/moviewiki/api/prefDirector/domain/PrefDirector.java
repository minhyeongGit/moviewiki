package com.moviewiki.api.prefDirector.domain;

import com.moviewiki.api.director.domain.Director;
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
@Table(name = "PREF_DIRECTORS")
@IdClass(PrefDirectorPK.class)
public class PrefDirector {

    @ManyToOne
    @JoinColumn(name ="USER_ID")
    @Id
    private User user;

    @ManyToOne
    @JoinColumn(name ="DIRECTOR_ID")
    @Id
    private Director director;

    @Column(name = "DIRECTOR_POINT")
    private double directorPoint;

    @Column(name = "DIRECTOR_REVIEW_COUNT")
    private int directorReviewCount;

    @Column(name = "DIRECTOR_REVIEW_DATE")
    private Date directorReviewDate;

}
