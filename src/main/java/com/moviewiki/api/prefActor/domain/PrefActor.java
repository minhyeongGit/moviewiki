package com.moviewiki.api.prefActor.domain;

import com.moviewiki.api.actor.domain.Actor;
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
@Table(name = "PREF_ACTORS")
@IdClass(PrefActorPK.class)
public class PrefActor {

    @ManyToOne
    @JoinColumn(name ="USER_ID")
    @Id
    private User user;

    @ManyToOne
    @JoinColumn(name ="ACTOR_ID")
    @Id
    private Actor actor;

    @Column(name = "ACTOR_POINT")
    private double actorPoint;
    @Column(name = "ACTOR_REVIEW_COUNT")
    private int actorReviewCount;

    @Column(name = "ACTOR_REVIEW_DATE")
    private Date actorReviewDate;

}
