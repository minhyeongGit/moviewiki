package com.moviewiki.api.prefActor.domain;

import com.moviewiki.api.actor.domain.Actor;
import com.moviewiki.api.user.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class PrefActorPK implements Serializable {

    private User user;
    private Actor actor;

}
